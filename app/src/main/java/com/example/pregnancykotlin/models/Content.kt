package com.example.pregnancykotlin.models

data class Content(
    var _id: String,
    var subTopicId: String,
    var image:String,
    var title: String,
    var text: String,
    var subContentIds: List<String>,
    var subContents: ArrayList<SubContent>,
    var userLike: Boolean
)
