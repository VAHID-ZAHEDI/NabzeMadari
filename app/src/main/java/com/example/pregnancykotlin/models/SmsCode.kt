package com.example.pregnancykotlin.models

import java.io.Serializable

data class SmsCode(
    var phoneNumber: String,
    var code: String,
    var expireTime: String,
    var expire: Boolean
) : Serializable
