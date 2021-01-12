package com.example.pregnancykotlin.login.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.models.User
import com.github.lany192.picker.NumberPicker
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.fragment_sign_up_user.*


class SignUpUserFragment : BaseFragment() {
    private val args: SignUpUserFragmentArgs by navArgs()
    private var age = 0
    private var height = 0
    private var weight = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        ll_register.gradientColor(arrayListOf("#09C6F9", "#045DE9"))
        np_height_reg.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        val values: MutableList<String> = ArrayList()
        for (i in 110..220) {
            values.add("$i سانتی متر ")
        }
        np_height_reg.displayedValues = values.toTypedArray()
        np_height_reg.maxValue = values.size - 1
        np_height_reg.minValue = 0
        np_height_reg.value = 150
        np_height_reg.setOnValueChangedListener { picker, oldVal, newVal ->
            tv_height_reg.text = values[newVal]
            height = (values[newVal].replace(" سانتی متر ", "")).toInt()


        }
        rule_weight_reg.setOnValueChangedListener {
            tv_weight_reg.text = "$it کیلوگرم "
            weight = it.toInt()

        }
        var currentYear = PersianCalendar().persianYear
        et_birthday.setOnClickListener {
            var typeface = Typeface.createFromAsset(activity?.assets, "Vazir.ttf")
            var picker = PersianDatePickerDialog(activity)
                .setPositiveButtonString("تایید")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
//            .setInitDate(initDate)
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(object : Listener {
                    override fun onDateSelected(persianCalendar: PersianCalendar?) {
                        et_birthday.setText(persianCalendar?.persianShortDate)
                        age = currentYear - persianCalendar?.persianYear!!

                    }

                    override fun onDismissed() {
                    }
                })


            picker.show()
        }

        bt_register.setOnClickListener {
            Log.d("nnn", "height: $height")
            Log.d("nnn", "weight: $weight")
            val loginViewModel = DaggerInstanceComponent.builder().build().getLoginViewModel()
            loginViewModel.signUp(
                User(
                    et_firstName.text.toString(),
                    et_lastName.text.toString(),
                    age,
                    args.phoneNumber,
                    weight, height
                )
            )
                .observe(viewLifecycleOwner, {
                    Log.d("bbb", "onViewCreated: ${it.status}")
                    when (it.status) {
                        Status.LOADING -> (activity as BaseActivity).showLoadingDialog()
                        Status.SUCCESS -> {
                            (activity as BaseActivity).dismissLoadingDialog()
                            loginViewModel.login(args.phoneNumber).observe(viewLifecycleOwner,
                                {
                                    when (it.status) {
                                        Status.SUCCESS -> {
                                            if (it.data?.accessToken != null) {
                                                var msp = DaggerPregnancyComponent.builder()
                                                    .setContext(activity as Context)
                                                    .build().getSafePref()
                                                msp.setSharedPreferences(
                                                    GlobalVariebles.TOKEN,
                                                    "Bearer ${it.data.accessToken}"
                                                )
                                                msp.setSharedPreferences(
                                                    GlobalVariebles.HAS_SIGN_IN,
                                                    true
                                                )
                                                val action =
                                                    SignUpUserFragmentDirections.actionSignUpUserFragmentToMainActivity()

                                                findNavController(view).navigate(action)
                                            }
                                        }
                                    }
                                })
                        }
                        Status.ERROR -> {
                            (activity as BaseActivity).dismissLoadingDialog()
                            Toast.makeText(
                                activity,
                                "یک خطا رخ داده است لطفا مجددا تلاش کنید!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
        }
    }

}