package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.MyNews
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

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("api/getContent")
    fun getContent(
        @Header("Authorization") authorization: String,
        @Field("subTopicId") subTopicId: String
    ): Single<Content>

    @Headers("Accept: application/json")
    @GET("/api/getAllBannerNews")
    fun getAllBannerNews(@Header("Authorization") authorization: String): Single<ArrayList<MyNews>>

    @Headers("Accept: application/json")
    @GET("/api/getAllContent")
    fun getAllContent(@Header("Authorization") authorization: String): Single<ArrayList<Content>>

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("/api/like")
    fun likeContent(
        @Header("Authorization") authorization: String,
        @Field("contentId") contentId: String):Single<Void>


}