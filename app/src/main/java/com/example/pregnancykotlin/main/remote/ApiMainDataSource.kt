package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.main.MainDataSource
import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.MyNews
import com.example.pregnancykotlin.models.SubTopic
import com.example.pregnancykotlin.models.Topic
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

}