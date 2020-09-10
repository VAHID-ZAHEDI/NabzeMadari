package com.example.pregnancykotlin.di.component

import com.example.pregnancykotlin.di.modules.TestModule
import com.example.pregnancykotlin.network.ApiClient
import com.google.gson.Gson
import dagger.Component

@Component(modules = [TestModule::class])
interface InstanceComponent {
    fun getGson(): Gson
}