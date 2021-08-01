package com.example.pregnancykotlin.main.view

import android.annotation.SuppressLint
import android.content.Context
//import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.models.User
import com.example.pregnancykotlin.profile.ProfileViewModel
import com.example.pregnancykotlin.utilities.Dialogs
import gradientColor
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_error.*


class ProfileFragment : BaseFragment() {
    lateinit var user: User
    private val model: ProfileViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

        bt_resources.setOnClickListener {
            Dialogs.showResourceDialog(activity as Context)
            Toast.makeText(activity,"sgal",Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeToolbarTitle("پروفایل")
        ll_profile.gradientColor(arrayListOf("#647DEE", "#7F53AC"))

        var profileViewModel = DaggerInstanceComponent.builder().build().getProfileViewModel()
        getData(profileViewModel)



        iv_edit.setOnClickListener {
            showBottomSheet()
        }

        model.userLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    user.firstName = it.data?.firstName.toString()
                    user.lastName = it.data?.lastName.toString()
                    user.height = it.data?.height!!
                    user.weight = it.data?.weight
                    tv_name.text = "${it.data?.firstName} ${it.data?.lastName}"
                    tv_weight.text = it.data?.weight.toString()
                    tv_height.text = it.data?.height.toString()
                }
            }

        })

        showCommunication()
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
                            user = it.data!!

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

    private fun showBottomSheet() {

        var direction = ProfileFragmentDirections.actionProfileFragmentToEditFragment(
                user.firstName,
                user.lastName,
                user.height,
                user.weight,
                getToken()
        )
        findNavController().navigate(direction)
//        activity?.supportFragmentManager?.let { editFragment.show(it, "") }
    }

    private fun showCommunication() {
        val commi = DaggerInstanceComponent.builder().build().getCommunication()

        bt_communication.setOnClickListener {
            activity?.supportFragmentManager?.let { commi.show(it, "") }
        }
    }


}