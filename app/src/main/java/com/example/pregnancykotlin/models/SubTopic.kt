package com.example.pregnancykotlin.models

data class SubTopic(
    var _id: String,
    var title: String,
    var description: List<String>,
    var videosCount: Byte,
    var pdfCount: Byte,
    var imagePath: String,
    var gradientColor: List<String>,
    var likeCount:Int
)
