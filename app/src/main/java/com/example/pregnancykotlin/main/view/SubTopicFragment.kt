package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.adapters.SubTopicAdapter

import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_sub_topic.*


class SubTopicFragment : Fragment() {
    private val args: SubTopicFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sub_topic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mainViewModel = DaggerInstanceComponent.builder().build().getMainViewModel()
        mainViewModel.getSubTopicsById("Bearer ${GlobalVariebles.token}", args.topicId)
            .observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> (activity as BaseActivity).showLoadingDialog()
                    Status.SUCCESS -> {
                        (activity as BaseActivity).dismissLoadingDialog()
                        rv_subTopics.setHasFixedSize(true)
                        rv_subTopics.layoutManager = LinearLayoutManager(activity)
                        var adapter = SubTopicAdapter(it.data!!)
                        rv_subTopics.adapter = adapter

                    }
                }
            })
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view).popBackStack(R.id.categoryFragment, false)


        }
    }

}