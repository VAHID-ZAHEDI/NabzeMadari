package com.example.pregnancykotlin.login.remote

import com.example.pregnancykotlin.network.ApiClient

class ApiProvider {
    companion object {
        var apiService: ApiService?= null
        fun apiProvider(): ApiService? {
            if (apiService == null) {
                apiService = ApiClient.client?.create(ApiService::class.java)
            }
            return apiService
        }
    }
}
