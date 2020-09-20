package com.example.pregnancykotlin.main

import com.example.pregnancykotlin.models.Topic
import io.reactivex.Single

interface MainDataSource {
    fun getAllTopic(token: String): Single<List<Topic>>?
}