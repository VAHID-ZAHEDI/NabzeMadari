package com.example.pregnancykotlin.calculator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pregnancykotlin.R
import gradientColor
import kotlinx.android.synthetic.main.fragment_child_birth.*
import kotlinx.android.synthetic.main.fragment_pregnancy_age.*

class PregnancyAgeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pregnancy_age, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cl_age.gradientColor(arrayListOf("#FC5296","#F67062"))

    }


}