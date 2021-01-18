package com.example.pregnancykotlin.models

import android.graphics.Color


data class SubContent(
    var _id: String,
    var title: String,
    var text: String,
    var mediaList: ArrayList<Media>,
    var icon: String,
    var created: String,
    var color: String

)