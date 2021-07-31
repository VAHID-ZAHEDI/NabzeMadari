package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.MainViewModel
import com.example.pregnancykotlin.main.adapters.SubTopicAdapter
import kotlinx.android.synthetic.main.fragment_sub_topic.*
import kotlinx.android.synthetic.main.layout_error.*


class SubTopicFragment : BaseFragment() {
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
        getData(mainViewModel)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Navigation.findNavController(view).popBackStack(R.id.categoryFragment, false)


        }
    }

    private fun getData(mainViewModel: MainViewModel) {
        mainViewModel.getSubTopicsById(getToken(), args.topicId)
            .observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.LOADING -> stateLayout.loading()
                    Status.SUCCESS -> {
                        stateLayout.content()
                        var subTopicAdapter = SubTopicAdapter(it.data!!)
                        rv_subTopics.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(activity)
                            adapter = subTopicAdapter
                        }
                    }
                    Status.ERROR -> {
                        stateLayout.info()
                        bt_retry.setOnClickListener {
                            getData(mainViewModel)
                        }
                    }
                }
            })
    }

}