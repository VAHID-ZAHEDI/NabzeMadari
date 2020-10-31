package com.example.pregnancykotlin.calculator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import changeIconColor
import com.example.pregnancykotlin.BaseFragment
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.login.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_calculator.*


class CalculatorFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeToolbarTitle("محاسبه گر")
        var fragments = arrayOf(
            BmiFragment() as Fragment,
            PregnancyAgeFragment() as Fragment,
            ChildBirthFragment() as Fragment
        )
        var adapter = ViewPagerAdapter(childFragmentManager, fragments, lifecycle)
        vp_cal.adapter = adapter
        vp_cal.isUserInputEnabled = false;

        tab_layout.changeIconColor(vp_cal)

    }


}