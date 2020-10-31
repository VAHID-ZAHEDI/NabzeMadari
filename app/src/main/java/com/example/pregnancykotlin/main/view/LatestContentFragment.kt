package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.adapters.LatestContentAdapter
import kotlinx.android.synthetic.main.fragment_latest_content.*


class LatestContentFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_latestContent.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        var mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
        mainViewModel.getAllContent(getToken()).observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    rv_latestContent.adapter = LatestContentAdapter(it.data!!)
                }
            }

        })
    }
}