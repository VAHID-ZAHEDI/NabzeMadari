package com.example.pregnancykotlin.di.component

import com.example.pregnancykotlin.di.modules.TestModule
import com.example.pregnancykotlin.login.LoginViewModel
import com.example.pregnancykotlin.main.MainViewModel
import com.example.pregnancykotlin.network.ApiClient
import com.google.gson.Gson
import dagger.Component

@Component
interface InstanceComponent {
    fun getLoginViewModel(): LoginViewModel
    fun getMainViewModel(): MainViewModel
}