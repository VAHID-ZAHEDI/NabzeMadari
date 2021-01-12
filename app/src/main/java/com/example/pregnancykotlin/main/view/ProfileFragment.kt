package com.example.pregnancykotlin.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.profile.ProfileViewModel
import gradientColor
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_error.*

class ProfileFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeToolbarTitle("پروفایل")
        ll_profile.gradientColor(arrayListOf("#647DEE", "#7F53AC"))

        var profileViewModel = DaggerInstanceComponent.builder().build().getProfileViewModel()
        getData(profileViewModel)


    }

    private fun getData(profileViewModel: ProfileViewModel) {

        profileViewModel.getUserInfo(getToken())
            .observe(viewLifecycleOwner, {
                when (it.status) {
                    Status.LOADING -> stateLayout.loading()
                    Status.SUCCESS -> {
                        stateLayout.content()
                        tv_name.text = "${it.data?.firstName} ${it.data?.lastName}"
                        tv_age.text = it.data?.age.toString()
                        tv_weight.text = it.data?.weight.toString()
                        tv_height.text = it.data?.height.toString()
                        bt_phoneNumber.text = it.data?.phoneNumber

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

}