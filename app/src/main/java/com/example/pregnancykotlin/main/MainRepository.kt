package com.example.pregnancykotlin.main

import com.example.pregnancykotlin.main.remote.ApiMainDataSource
import com.example.pregnancykotlin.models.Topic
import io.reactivex.Single

class MainRepository : MainDataSource {
    var apiMainDataSource = ApiMainDataSource()
    override fun getAllTopic(token: String): Single<List<Topic>>? {
        return apiMainDataSource.getAllTopic(token)
    }

}