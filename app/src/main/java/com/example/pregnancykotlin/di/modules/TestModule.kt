package com.example.pregnancykotlin.di.modules

import com.example.pregnancykotlin.models.ErrorModel
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Module
import dagger.Provides

@Module
class TestModule {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}