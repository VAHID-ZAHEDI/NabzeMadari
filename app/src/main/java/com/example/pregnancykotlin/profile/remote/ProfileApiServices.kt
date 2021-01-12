package com.example.pregnancykotlin.profile.remote

import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.User
import io.reactivex.Single
import retrofit2.http.*

interface ProfileApiServices {
    @GET("/user")
    @Headers("Accept: application/json")
    fun getUserInfo(@Header("Authorization") authorization: String): Single<User>

    @FormUrlEncoded
    @POST("/bookmark")
    @Headers("Accept: application/json")
    fun setUnsetBookmark(
        @Header("Authorization") authorization: String,
        @Field("contentId") contentId: String
    ): Single<Void>

    @Headers("Accept: application/json")
    @GET("getBookmarkContent")
    fun getBookmarkContent(@Header("Authorization") authorization: String): Single<List<Content>>
}