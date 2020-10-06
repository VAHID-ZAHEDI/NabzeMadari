package com.example.pregnancykotlin.main.view

import android.os.Bundle
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
import com.example.pregnancykotlin.main.MainViewModel
import com.example.pregnancykotlin.main.adapters.MainTopicAdapter
import com.example.pregnancykotlin.models.Topic
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : BaseFragment() {
    //    private var view: View? = null
    private var mainViewModel: MainViewModel? = null
    lateinit var topics: List<Topic>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = null
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_category, container, false)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mainViewModel == null) {
            mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
            mainViewModel!!.getAllTopics("Bearer ${GlobalVariebles.token}")
                .observe(viewLifecycleOwner,
                    Observer {
                        when (it.status) {
                            Status.LOADING -> (activity as BaseActivity).showLoadingDialog()
                            Status.SUCCESS -> {
                                (activity as BaseActivity).dismissLoadingDialog()
                                topics = it.data!!
                                var headerAdapter = MainTopicAdapter(it.data!!)
                                rv_topics.layoutManager = LinearLayoutManager(activity)
                                rv_topics.adapter = headerAdapter

                            }
                        }


                    })
        } else if (topics!=null){
            var mainTopicAdapter = MainTopicAdapter(topics)
            rv_topics.layoutManager = LinearLayoutManager(this.context)
            rv_topics.adapter = mainTopicAdapter
        }


    }

}