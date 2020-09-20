package com.example.pregnancykotlin.login

import com.example.pregnancykotlin.login.remote.ApiLoginDataSource
import com.example.pregnancykotlin.models.SmsCode
import com.example.pregnancykotlin.models.TokenInfo
import com.example.pregnancykotlin.models.User
import io.reactivex.Observable
import io.reactivex.Single


class LoginRepository : LoginDataSource {
    var apiLoginDataSource = ApiLoginDataSource()
    override fun login(phoneNumber: String?): Single<TokenInfo> {
        return apiLoginDataSource.login(phoneNumber)
    }

    override
    fun generateCode(phoneNumber: String): Single<SmsCode> {
        return apiLoginDataSource.generateCode(phoneNumber)
    }

    override
    fun validateCode(phoneNumber: String, otp: String): Single<User> {
        return apiLoginDataSource.validateCode(phoneNumber, otp)
    }

    override fun signUp(user: User): Single<User> {
       return apiLoginDataSource.signUp(user)
    }
}