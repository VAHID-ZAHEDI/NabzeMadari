package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.models.*
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
        @Field("contentId") contentId: String
    ): Single<Void>

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("/api/comments")
    fun getContentComments(
        @Header("Authorization") authorization: String,
        @Field("contentId") contentId: String,
        @Field("page") page: Int,
        @Field("size") size: Int
    ): Single<CommentsPaging>

    @Headers("Accept: application/json")
    @POST("/api/addComment")
    fun addNewComment(
        @Header("Authorization") authorization: String,
        @Body addComment: AddComment
    ): Single<Void>

}