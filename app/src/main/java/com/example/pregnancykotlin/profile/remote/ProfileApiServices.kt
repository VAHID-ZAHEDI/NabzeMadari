package com.example.pregnancykotlin.profile.remote

import com.example.pregnancykotlin.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ProfileApiServices {
    @GET("/user")
    @Headers("Accept: application/json")
    fun getUserInfo(@Header("Authorization") authorization: String): Single<User>
}