package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pregnancykotlin.R
import com.zkk.view.rulerview.RulerView
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var colors = intArrayOf(Color.parseColor("#A4508B"), Color.parseColor("#5F0A87"))
//        rl_card.gradientColor(colors)
        ruler_height.setOnValueChangeListener(RulerView.OnValueChangeListener {

        })
    }

}