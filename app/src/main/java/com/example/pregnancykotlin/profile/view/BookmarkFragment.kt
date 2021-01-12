package com.example.pregnancykotlin.profile.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.adapters.LatestContentAdapter
import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.profile.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.layout_error.*

class BookmarkFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var profileViewModel = DaggerInstanceComponent.builder().build().getProfileViewModel()
        getData(profileViewModel)

    }

    private fun getData(profileViewModel: ProfileViewModel) {
        profileViewModel.getBookmarkContent(getToken()).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> stateLayout.loading()
                Status.SUCCESS -> {
                    if (it.data?.isEmpty()!!) {
                        Log.d("mkmk", "getData: success")
                        stateLayout.info()
//                        stateLayout.showEmpty(
//                            StateLayout.StateInfo(
//                                null,
//                                "محتوایی در لیست علاقه مندی وجود ندارد",
//                                "salam",
//                                null,
//                                StateLayout.State.EMPTY,
//                                null,
//                                null,
//                                null
//                            )
//                        )
                    } else {
                        stateLayout.content()
                        initBookmarkRecyclerView(it?.data!!)
                    }
                }
                Status.ERROR -> {
                    stateLayout.info()
                    bt_retry.setOnClickListener {
                        getData(profileViewModel)
                    }
                }
            }

        })
    }

    private fun initBookmarkRecyclerView(contents: List<Content>) {
        rv_bookmark.apply {
            layoutManager = GridLayoutManager(activity, 2)
            hasFixedSize()
            adapter = LatestContentAdapter(
                contents as ArrayList<Content>,
                LatestContentAdapter.ContentFrom.PROFILE
            )
        }
    }

}