package com.example.pregnancykotlin.models

data class Content(
    var id: String,
    var title: String,
    var text: String,
    var mediaList: ArrayList<Media>,
    var color: Int
)
