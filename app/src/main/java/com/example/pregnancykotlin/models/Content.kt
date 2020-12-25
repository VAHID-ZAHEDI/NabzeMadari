package com.example.pregnancykotlin.models

data class Content(
    var _id: String,
    var subTopicId: String,
    var title: String,
    var text: String,
    var mediaList: ArrayList<Media>,
    var color: Int,
    var userLike:Boolean
)
