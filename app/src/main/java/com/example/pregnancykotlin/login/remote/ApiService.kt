package com.example.pregnancykotlin.login.remote

import android.provider.Telephony
import com.example.pregnancykotlin.models.SmsCode
import com.example.pregnancykotlin.models.TokenInfo
import com.example.pregnancykotlin.models.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field

import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST


interface ApiService {
    @POST("api/auth/generateOtp")
    @FormUrlEncoded
    fun generateOtpCode(@Field("phoneNumber") phoneNumber: String): Single<SmsCode>

    @POST("api/auth/validateOtp")
    @FormUrlEncoded
    fun validateOtpCode(
        @Field("phoneNumber") phoneNumber: String,
        @Field("otp") otp: String
    ): Single<User>


    @POST("api/auth/signin")
    @FormUrlEncoded
    fun singInWithPhoneNumber(@Field("phoneNumber") phoneNumber: String?): Single<TokenInfo>

    @POST("api/auth/signup")
    fun signUp(@Body user: User): Single<User>

}
