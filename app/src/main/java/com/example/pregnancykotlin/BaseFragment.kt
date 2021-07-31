package com.example.pregnancykotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.utilities.PrefObject
import kotlinx.android.synthetic.main.activity_main.*

open class BaseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    fun getToken(): String {
        return DaggerPregnancyComponent.builder().setContext(activity as Context).build()
            .getSafePref().getSharedPreferences(GlobalVariables.TOKEN, PrefObject.STRING) as String
    }

    fun changeToolbarTitle(title: String) {
        (activity as BaseActivity).toolbar.title = title
    }

}