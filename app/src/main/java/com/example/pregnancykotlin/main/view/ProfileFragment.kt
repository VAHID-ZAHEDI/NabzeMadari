package com.example.pregnancykotlin.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.models.User
import com.example.pregnancykotlin.profile.ProfileViewModel
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
//        val editFragment = DaggerInstanceComponent.builder().build().getEditFragment()
//        editFragment.getUser().observe(viewLifecycleOwner, Observer {
//            Toast.makeText(activity,"salam",Toast.LENGTH_LONG).show()
//            tv_name.text = "${it.firstName} ${it.lastName}"
//            tv_weight.text=it.weight.toString()
//            tv_height.text=it.height.toString()
//        })
        model.userLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    user.firstName=it.data?.firstName.toString()
                    user.lastName=it.data?.lastName.toString()
                    user.height=it.data?.height!!
                    user.weight=it.data?.weight
                    tv_name.text = "${it.data?.firstName} ${it.data?.lastName}"
                    tv_weight.text = it.data?.weight.toString()
                    tv_height.text = it.data?.height.toString()
                }
            }

        })


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


}