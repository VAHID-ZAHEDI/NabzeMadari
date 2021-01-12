package com.example.pregnancykotlin.models

data class CommentsPaging(
    var content: ArrayList<Comment>?,
    var totalPages: Int,
    var totalElements: Int,
    var last: Boolean
)