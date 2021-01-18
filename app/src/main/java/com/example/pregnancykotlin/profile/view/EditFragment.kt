package com.example.pregnancykotlin.profile.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.models.User
import com.example.pregnancykotlin.profile.ProfileViewModel
import com.example.pregnancykotlin.utilities.Dialogs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_edit.*
import javax.inject.Inject

class EditFragment @Inject constructor() : BottomSheetDialogFragment() {
    private val args: EditFragmentArgs by navArgs()
    private var height: Int =0
    private var weight =0
    private val profileViewModel: ProfileViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        et_firstName.setText(args.firstName)
        et_lastName.setText(args.lastName)
        height=args.height
        weight=args.weight
        rule_weight_reg.currentValue = args.weight.toFloat()
        rule_weight_reg.setOnValueChangedListener {
            tv_weight_reg.text = "$it کیلوگرم "
            weight = it.toInt()

        }
        val values: MutableList<String> = ArrayList()
        for (i in 110..220) {
            values.add("$i سانتی متر ")
        }
        np_height_reg.displayedValues = values.toTypedArray()
        np_height_reg.maxValue = values.size - 1
        np_height_reg.minValue = 0
        np_height_reg.value = args.height
        np_height_reg.setOnValueChangedListener { picker, oldVal, newVal ->
            tv_height_reg.text = values[newVal]
            height = (values[newVal].replace(" سانتی متر ", "")).toInt()
        }
        bt_update.setOnClickListener {
            updateUserInfo()
        }


    }

    private fun updateUserInfo() {
//        var profileViewModel = DaggerInstanceComponent.builder().build().getProfileViewModel()
        profileViewModel.updateUserInfo(
            args.token,
            et_firstName.text.toString(),
            et_lastName.text.toString(),
            height,
            weight
        ).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> Dialogs.showLoadingDialog(activity as Context)
                Status.SUCCESS -> {
                    Dialogs.dismissLoadingDialog()
                    dismiss()
                }
            }


        })
    }



}