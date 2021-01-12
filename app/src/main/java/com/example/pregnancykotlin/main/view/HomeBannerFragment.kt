package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.adapters.HomeBannerAdapter
import com.example.pregnancykotlin.models.MyNews
import kotlinx.android.synthetic.main.fragment_home_banner.*
import kotlin.math.abs

class HomeBannerFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
        mainViewModel.getAllBannerNews(getToken())
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.SUCCESS -> setupViewPager(it.data!!)

                }
            })
    }

    private fun setupViewPager(myNewsList: ArrayList<MyNews>) {

        var position = 0
        vp_home.adapter =
            HomeBannerAdapter(
                myNewsList!!
            )
        vp_home.clipToPadding = false
        vp_home.clipChildren = false
        vp_home.offscreenPageLimit = 3
        vp_home.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            var r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        vp_home.setPageTransformer(compositePageTransformer)
//        fixedRateTimer("timer", false, 0, 10000) {
//            if (vp_home.currentItem != myNewsList.size - 1) {
//                position++
//            } else {
//                position = 0
//            }
//            vp_home.currentItem = position
//
//        }
        home_indicator.setViewPager2(vp_home)
    }


}