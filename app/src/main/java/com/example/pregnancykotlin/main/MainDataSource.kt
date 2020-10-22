package com.example.pregnancykotlin.main

import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.SubTopic
import com.example.pregnancykotlin.models.Topic
import io.reactivex.Single

interface MainDataSource {
    fun getAllTopic(token: String): Single<List<Topic>>?
    fun getSubTopicsById(token: String, topicId: String): Single<ArrayList<SubTopic>>?
    fun getContent(token: String, subTopicId: String):Single<Content>?
}