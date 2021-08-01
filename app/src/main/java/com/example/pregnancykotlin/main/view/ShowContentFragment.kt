package com.example.pregnancykotlin.main.view

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.MainViewModel
import com.example.pregnancykotlin.main.adapters.BannerAdapter
import com.example.pregnancykotlin.main.adapters.CommentAdapter
import com.example.pregnancykotlin.models.AddComment
import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.Media
import com.example.pregnancykotlin.models.SubContent
import com.example.pregnancykotlin.utilities.Dialogs
import com.google.android.material.tabs.TabLayout
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.fragment_show_content.*
import kotlinx.android.synthetic.main.layout_error.*
import org.sufficientlysecure.htmltextview.HtmlResImageGetter
import startFadeIn
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView
import kotlin.math.abs


class ShowContentFragment : BaseFragment() {
    private val args: DetailsActivityArgs by navArgs()
    private var mainViewModel: MainViewModel? = null
    private lateinit var content: Content
    private lateinit var contentId: String
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var adapter:BannerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = null
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_show_content, container, false)
        }
        MaterialShowcaseView.Builder(activity)
            .setTarget(view?.findViewById(R.id.bt_bookmark))
            .setDismissText("متوجه شدم")
            .setContentText("شما با استفاده از این ایکون میتوانید محتوا نمایش داده شده را به لیست علاقه مندی خود اضافه کنید و سپس در قسمت پروفایل آن را مشاهده کنید.")
            .setDelay(500)
            .singleUse("bookmark")
            .show()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).initToolbar(toolbar, true)
        if (mainViewModel == null) {
            mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
            getData()
        } else {
            toolbar.title = content?.title
            collapsing_toolbar_layout.title = content?.title
            tv_text_html.setHtml(content.text.replace("¬", " ‌‌"))
        }
        manageLike()
        initCommentRecyclerView()
        manageFAB()
        writeComment()

    }

    private fun getData() {
//        htmlSpanner= HtmlSpanner(tv_text_html.currentTextColor, tv_text_html.textSize)
        mainViewModel!!.getContent(getToken(), args.subTopicId)
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> stateLayout.loading()
                    Status.SUCCESS -> {
                        stateLayout.content()
                        toolbar.title = it.data?.title
        //                        collapsing_toolbar_layout.title = it.data?.title
        //                        tv_text_html.setHtml(it.data?.text?.replace("¬", " ‌‌")!!)
                        content = it.data!!
                        contentId = it.data._id
                        bt_like.isLiked = content.userLike
                        getCommentData()
                        manageBookmark()
                        initSubContent(it.data.subContents)
                    }
                    Status.ERROR -> {
                        stateLayout.info()
                        bt_retry.setOnClickListener {
                            getData()
                        }
                    }

                }

            }
    }

    private fun manageLike() {
        bt_like.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                mainViewModel?.likeContent(getToken(), contentId!!)?.observe(viewLifecycleOwner,
                    Observer {

                    })
            }

            override fun unLiked(likeButton: LikeButton) {
                mainViewModel?.likeContent(getToken(), contentId!!)?.observe(viewLifecycleOwner,
                    Observer {

                    })
            }
        })
    }

    private fun manageBookmark() {
        var profileViewModel = DaggerInstanceComponent.builder().build().getProfileViewModel()
        profileViewModel.getBookmarkContent(getToken()).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    for (i in it.data?.indices!!) {
                        if (it.data[i]._id == contentId) {
                            bt_bookmark.isLiked = true
                            break
                        } else {
                            bt_bookmark.isLiked = false
                        }
                    }
                }
            }
        })
        bt_bookmark.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                profileViewModel.setUnsetBookmark(getToken(), contentId)
                    .observe(viewLifecycleOwner, Observer {

                    })
            }

            override fun unLiked(likeButton: LikeButton?) {
                profileViewModel.setUnsetBookmark(getToken(), contentId)
                    .observe(viewLifecycleOwner, Observer {

                    })
            }
        })

    }

    private fun setupViewPager(bannerData: ArrayList<Media>?) {
         adapter = BannerAdapter(bannerData!!)
        vp_banner.startFadeIn()
        vp_banner.adapter = adapter
        vp_banner.clipToPadding = false
        vp_banner.clipChildren = false
        vp_banner.offscreenPageLimit = 3
        vp_banner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            var r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        vp_banner.setPageTransformer(compositePageTransformer)

        worm_dots_indicator.setViewPager2(vp_banner)


    }


    private fun initCommentRecyclerView() {
        rv_comments.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(activity)
            val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            commentAdapter = CommentAdapter()
            adapter = commentAdapter

        }
    }

    private fun getCommentData() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel?.getComments(
            getToken(),
            contentId
        )
            ?.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    commentAdapter.submitList(it)
                } else {
                    Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
                }
            })

    }

    private fun manageFAB() {
        nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                fab_comment.hide()
                fab_comment.animate()
            } else {
                fab_comment.show()
            }
        })

    }

    private fun writeComment() {
        fab_comment.setOnClickListener {
            Dialogs.showWriteCommentDialog(activity as Context)
                .observe(viewLifecycleOwner, Observer {
                    mainViewModel?.addNewComment(getToken(), AddComment(it, contentId))
                        ?.observe(viewLifecycleOwner,
                            Observer {
                                when (it.status) {
                                    Status.SUCCESS -> {
                                        Toast.makeText(
                                            context,
                                            "نظر شما با موفقیت ثبت شد",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        getCommentData()
                                    }
                                }
                            })
                })
        }
    }

    private fun initSubContent(subContents: ArrayList<SubContent>) {
        for (i in 0 until subContents.size) {
            tab_layout.addTab(tab_layout.newTab().setText(subContents[i].title))
        }
        tv_text_html.setHtml(subContents[0].text.replace("¬", " ‌‌"))
        setupViewPager(subContents[0].mediaList)

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    tv_text_html.startFadeIn()
                    tv_text_html.setHtml(subContents[p0.position].text.replace("¬", " ‌‌"))
                    setupViewPager(subContents[p0.position].mediaList)
//                    tab_layout.setSelectedTabIndicatorColor(Color.parseColor(subContents[p0.position].color))

                }
            }


        })

//        tv_text.setText()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.stop()

    }

}