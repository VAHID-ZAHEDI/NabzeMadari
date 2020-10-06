package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.models.SubTopic
import com.example.pregnancykotlin.models.Topic
import io.reactivex.Single
import retrofit2.http.*

interface MainApiService {
    @Headers("Accept: application/json")
    @GET("/api/getAllMainTopic")
    fun getAllTopics(@Header("Authorization") authorization: String): Single<List<Topic>>?

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("/api/getSubTopic")
    fun getSubTopicsById(
        @Header("Authorization") authorization: String,
        @Field("topicId") topicId: String
    ): Single<ArrayList<SubTopic>>?
}