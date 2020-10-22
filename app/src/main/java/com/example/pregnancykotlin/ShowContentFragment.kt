package com.example.pregnancykotlin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.adapters.BannerAdapter
import com.example.pregnancykotlin.main.view.DetailsActivityArgs
import com.example.pregnancykotlin.models.Media
import kotlinx.android.synthetic.main.fragment_show_content.*
import kotlin.math.abs

class ShowContentFragment : BaseFragment() {
    private val args: DetailsActivityArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).initToolbar(toolbar)
        var mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
        mainViewModel.getContent("Bearer ${GlobalVariebles.token}", args.subTopicId)
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> (activity as BaseActivity).showLoadingDialog()
                    Status.SUCCESS -> {
                        (activity as BaseActivity).dismissLoadingDialog()
                        toolbar.title = it.data?.title
                        collapsing_toolbar_layout.title=it.data?.title
                        setupViewPager(it.data?.mediaList)
                        tv_text.text = it.data?.text
                    }

                }

            })

    }

    private fun setupViewPager(bannerData: ArrayList<Media>?) {
        Log.d("qqq", "setupViewPager: ${bannerData?.size}")
        vp_banner.adapter =
            BannerAdapter(
                bannerData!!
            )
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

}