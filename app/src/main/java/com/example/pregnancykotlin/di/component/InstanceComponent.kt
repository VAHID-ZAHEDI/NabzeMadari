package com.example.pregnancykotlin.di.component

import com.example.pregnancykotlin.calculator.CalculatorViewModel
import com.example.pregnancykotlin.di.modules.TestModule
import com.example.pregnancykotlin.login.LoginViewModel
import com.example.pregnancykotlin.main.MainViewModel
import com.example.pregnancykotlin.network.ApiClient
import com.example.pregnancykotlin.profile.ProfileViewModel
import com.google.gson.Gson
import dagger.Component

@Component
interface InstanceComponent {
    fun getLoginViewModel(): LoginViewModel
    fun getMainViewModel(): MainViewModel
    fun getCalculatorViewModel(): CalculatorViewModel
    fun getProfileViewModel(): ProfileViewModel
}