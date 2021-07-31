package com.example.pregnancykotlin.calculator.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
//import com.github.lany192.picker.NumberPicker
import com.shawnlin.numberpicker.NumberPicker
import gradientColor
import kotlinx.android.synthetic.main.fragment_bmi.*
import kotlinx.android.synthetic.main.fragment_bmi.view.*
import java.text.DecimalFormat

class BmiFragment : Fragment() {
    var weight: Float = 0f
    var height: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bmi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cl_bmi.gradientColor(arrayListOf("#09C6F9", "#045DE9"))

        np_height.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        val values: MutableList<String> = ArrayList()
        for (i in 110..220) {
            values.add("$i سانتی متر ")
        }
        np_height.displayedValues = values.toTypedArray()
        np_height.maxValue = values.size - 1
        np_height.minValue = 0
        np_height.
        np_height.typeface = Typeface.createFromAsset(activity?.assets,"Vazir.ttf")
        tv_height.text = values[0]
//        height = values[0].toInt()

        np_height.setOnValueChangedListener { picker, oldVal, newVal ->
            tv_height.text = values[newVal]
            height = (values[newVal].replace(" سانتی متر ", "")).toInt()
        }
        tv_weight.text = "${rule_weight.currentValue} کیلوگرم "
        weight = rule_weight.currentValue
        rule_weight.setOnValueChangedListener {
            tv_weight.text = "$it کیلوگرم "
            weight = it
        }
        var calculatorViewModel = DaggerInstanceComponent.builder().build().getCalculatorViewModel()
        bt_calculateBmi.setOnClickListener {
            calculatorViewModel.calculateBmi(weight, height).observe(viewLifecycleOwner, Observer {
                val dec = DecimalFormat(".##")

                tv_bmi.text = "بی ام آی شما : ${dec.format(it)} "
            })
        }

    }

}