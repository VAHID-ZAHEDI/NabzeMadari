package com.example.pregnancykotlin.main

import com.example.pregnancykotlin.main.remote.ApiMainDataSource
import com.example.pregnancykotlin.models.*
import io.reactivex.Single

class MainRepository : MainDataSource {
    var apiMainDataSource = ApiMainDataSource()
    override fun getAllTopic(token: String): Single<List<Topic>>? {
        return apiMainDataSource.getAllTopic(token)
    }

    override fun getSubTopicsById(token: String, topicId: String): Single<ArrayList<SubTopic>>? {
        return apiMainDataSource?.getSubTopicsById(token, topicId)
    }

    override fun getContent(token: String, subTopicId: String): Single<Content>? {
        return apiMainDataSource.getContent(token, subTopicId)
    }

    override fun getAllBannerNews(token: String): Single<ArrayList<MyNews>>? {
        return apiMainDataSource.getAllBannerNews(token)
    }

    override fun getAllContent(token: String): Single<ArrayList<Content>>? {
        return apiMainDataSource.getAllContent(token)
    }

    override fun likeContent(token: String, contentId: String): Single<Void>? {
        return apiMainDataSource.likeContent(token, contentId)
    }

    override fun getContentComments(
        token: String,
        contentId: String,
        page: Int,
        size: Int
    ): Single<CommentsPaging>? {
        return apiMainDataSource.getContentComments(token, contentId, page, size)
    }

    override fun addNewComment(token: String, addComment: AddComment): Single<Void>? {
        return apiMainDataSource.addNewComment(token, addComment)
    }


}