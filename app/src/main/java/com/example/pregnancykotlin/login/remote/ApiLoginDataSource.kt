package com.example.pregnancykotlin.login

import com.example.pregnancykotlin.models.TokenInfo
import io.reactivex.Single

class ApiLoginDataSource : LoginDataSource {
    private var apiService: ApiService = ApiProvider.apiProvider()!!
    override fun login(phoneNumber: String?): Single<TokenInfo> {
        return apiService.singInWithPhoneNumber(phoneNumber)
    }
}