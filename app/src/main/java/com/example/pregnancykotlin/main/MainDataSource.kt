package com.example.pregnancykotlin.main

import com.example.pregnancykotlin.models.*
import io.reactivex.Single

interface MainDataSource {
    fun getAllTopic(token: String): Single<List<Topic>>?
    fun getSubTopicsById(token: String, topicId: String): Single<ArrayList<SubTopic>>?
    fun getContent(token: String, subTopicId: String): Single<Content>?
    fun getAllBannerNews(token: String): Single<ArrayList<MyNews>>?
    fun getAllContent(token: String): Single<ArrayList<Content>>?
    fun likeContent(token: String, contentId: String): Single<Void>?
    fun getContentComments(token: String, contentId: String, page: Int, size: Int):Single<CommentsPaging>?
    fun addNewComment(token: String,addComment: AddComment):Single<Void>?
}