package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.network.ApiClient

class MainApiProvider {
    companion object {
        var mainApiService: MainApiService? = null
        fun mainApiProvider(): MainApiService? {
            if (mainApiService == null) {
                mainApiService = ApiClient.provideRetrofit()?.create(MainApiService::class.java)

            }
            return mainApiService
        }

    }
}