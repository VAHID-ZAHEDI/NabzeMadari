package com.example.pregnancykotlin.di.component

import android.content.Context
import com.example.pregnancykotlin.di.modules.TestModule
import com.example.pregnancykotlin.login.LoginViewModel
import com.example.pregnancykotlin.network.ApiClient
import com.example.pregnancykotlin.utilities.SafePref
import com.example.pregnancykotlin.utilities.Zcript
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [TestModule::class, ApiClient::class])
interface PregnancyComponent {
    fun getZcrypt(): Zcript
    fun getSafePref(): SafePref



    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext(context: Context): Builder
        fun build(): PregnancyComponent
    }
}