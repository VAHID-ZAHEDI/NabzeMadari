package com.example.pregnancykotlin.main.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.adapters.HeaderAdapter
import com.example.pregnancykotlin.utilities.Dialogs
import gradientColor
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.item_header.*

class CategoryFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
        mainViewModel.getAllTopics("Bearer ${GlobalVariebles.token}").observe(viewLifecycleOwner,
            Observer {
                when (it.status) {
                    Status.LOADING -> (activity as BaseActivity).showLoadingDialog()
                    Status.SUCCESS -> {
                        (activity as BaseActivity).dismissLoadingDialog()
                        var headerAdapter = HeaderAdapter(it.data!!)
                        rv_topics.layoutManager = LinearLayoutManager(activity)
                        rv_topics.adapter = headerAdapter

                    }
                }


            })

    }

}