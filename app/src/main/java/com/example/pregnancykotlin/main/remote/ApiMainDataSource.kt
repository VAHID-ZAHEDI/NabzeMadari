package com.example.pregnancykotlin.main.remote

import com.example.pregnancykotlin.main.MainDataSource
import com.example.pregnancykotlin.models.Topic
import io.reactivex.Single

class ApiMainDataSource : MainDataSource {
    private var mainApiService = MainApiProvider.mainApiProvider()
    override fun getAllTopic(token: String): Single<List<Topic>>? {
        return mainApiService?.getAllTopics(token)

    }

}