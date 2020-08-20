package com.example.pregnancykotlin.login

import com.example.pregnancykotlin.models.TokenInfo
import io.reactivex.Single
import retrofit2.http.Field

import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST


interface ApiService {
    @POST("/api/auth/signin")
    @FormUrlEncoded
    fun singInWithPhoneNumber(@Field("phoneNumber") phoneNumber: String?): Single<TokenInfo>
}
