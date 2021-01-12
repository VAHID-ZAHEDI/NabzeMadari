package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.main.MainDataSource
import com.example.pregnancykotlin.models.*
import io.reactivex.Single

class ApiMainDataSource : MainDataSource {
    private var mainApiService = MainApiProvider.mainApiProvider()
    override fun getAllTopic(token: String): Single<List<Topic>>? {
        return mainApiService?.getAllTopics(token)

    }

    override fun getSubTopicsById(token: String, topicId: String): Single<ArrayList<SubTopic>>? {
        return mainApiService?.getSubTopicsById(token, topicId)
    }

    override fun getContent(token: String, subTopicId: String): Single<Content>? {
        return mainApiService?.getContent(token, subTopicId)
    }

    override fun getAllBannerNews(token: String): Single<ArrayList<MyNews>>? {
        return mainApiService?.getAllBannerNews(token)
    }

    override fun getAllContent(token: String): Single<ArrayList<Content>>? {
        return mainApiService?.getAllContent(token)
    }

    override fun likeContent(token: String, contentId: String): Single<Void>? {
        return mainApiService?.likeContent(token, contentId)
    }

    override fun getContentComments(
        token: String,
        contentId: String,
        page: Int,
        size: Int
    ): Single<CommentsPaging>? {
        return mainApiService?.getContentComments(token, contentId, page, size)
    }

    override fun addNewComment(token: String, addComment: AddComment): Single<Void>? {
        return mainApiService?.addNewComment(token,addComment)
    }

}