package com.example.pregnancykotlin.main

import com.example.pregnancykotlin.main.remote.ApiMainDataSource
import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.MyNews
import com.example.pregnancykotlin.models.SubTopic
import com.example.pregnancykotlin.models.Topic
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


}