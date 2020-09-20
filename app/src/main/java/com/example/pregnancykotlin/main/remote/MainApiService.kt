package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.models.Topic
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MainApiService {
    @Headers("Accept: application/json")
    @GET("/api/getAllHeaders")
    fun getAllTopics(@Header("Authorization") authorization: String): Single<List<Topic>>?
}