package com.example.pregnancykotlin.login.remote

import com.example.pregnancykotlin.login.LoginDataSource
import com.example.pregnancykotlin.models.SmsCode
import com.example.pregnancykotlin.models.TokenInfo
import io.reactivex.Single

class ApiLoginDataSource : LoginDataSource {
    private var apiService: ApiService = ApiProvider.apiProvider()!!
    override fun login(phoneNumber: String?): Single<TokenInfo> {
        return apiService.singInWithPhoneNumber(phoneNumber)
    }

    override fun generateCode(phoneNumber: String): Single<SmsCode> {
        return apiService.generateOtpCode(phoneNumber)
    }

    override fun validateCode(phoneNumber: String, otpCode: String): Single<TokenInfo> {
        return apiService.validateOtpCode(phoneNumber, otpCode)
    }

}