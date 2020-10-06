package com.example.pregnancykotlin.main

import com.example.pregnancykotlin.main.remote.ApiMainDataSource
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

}