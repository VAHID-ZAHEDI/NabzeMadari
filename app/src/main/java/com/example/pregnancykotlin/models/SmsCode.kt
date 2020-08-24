package com.example.pregnancykotlin.models

data class SmsCode(
    var code: String,
    var expireTime: String,
    var expire: Boolean
)
