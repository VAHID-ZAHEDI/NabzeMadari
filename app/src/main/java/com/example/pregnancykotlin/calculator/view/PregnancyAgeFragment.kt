package com.example.pregnancykotlin.calculator.view

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.calculator.Calculator
import gradientColor
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.fragment_pregnancy_age.*

class PregnancyAgeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregnancy_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cl_age.gradientColor(arrayListOf("#FC5296", "#F67062"))
        val calculator = Calculator()
        var currentDate = PersianCalendar()
        bt_dateSelect_age.setOnClickListener {
            var typeface = Typeface.createFromAsset(activity?.assets, "Vazir.ttf")
            var picker = PersianDatePickerDialog(activity)
                .setPositiveButtonString("تایید")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1330)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
//            .setInitDate(initDate)
                .setActionTextColor(Color.GRAY)
                .setTypeFace(typeface)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(object : Listener {
                    override fun onDateSelected(persianCalendar: PersianCalendar?) {


                        tv_date_age.text = persianCalendar?.persianLongDate
                        tv_description_age.text = calculator.getWeeksDifference(
                            persianCalendar!!,
                            currentDate
                        )


                    }

                    override fun onDismissed() {
                    }
                })


            picker.show()
        }


    }


}