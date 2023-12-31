package com.example.pregnancykotlin.login

import com.example.pregnancykotlin.models.SmsCode
import com.example.pregnancykotlin.models.TokenInfo
import com.example.pregnancykotlin.models.User
import io.reactivex.Single


interface LoginDataSource {
    fun login(phoneNumber: String?): Single<TokenInfo>
    fun generateCode(phoneNumber: String): Single<SmsCode>
    fun validateCode(phoneNumber: String, otp: String): Single<User>
    fun signUp(user: User): Single<User>

}