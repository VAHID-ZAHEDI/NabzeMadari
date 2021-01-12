package com.example.pregnancykotlin.models

data class Comment(
    var _id: String,
    var created: String,
    var text: String,
    var contentId: String,
    var userName: String,
    var userImagePath: String,
    var userId: String
){

}
