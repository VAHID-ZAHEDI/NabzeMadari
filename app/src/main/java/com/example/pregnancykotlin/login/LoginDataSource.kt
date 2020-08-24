package com.example.pregnancykotlin.login

import com.example.pregnancykotlin.models.TokenInfo
import io.reactivex.Single


interface LoginDataSource {
    fun login(phoneNumber: String?): Single<TokenInfo>
}