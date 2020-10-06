package com.example.pregnancykotlin.calculator.view

import android.R.attr.typeface
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pregnancykotlin.R
import gradientColor
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.fragment_child_birth.*


class ChildBirthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child_birth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cl_edd.gradientColor(arrayListOf("#38ef7d", "#11998e"))
        bt_dateSelect.setOnClickListener {
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
                        tv_date.text = persianCalendar?.persianLongDate
                    }

                    override fun onDismissed() {
                    }
                })


            picker.show()
        }

    }

}