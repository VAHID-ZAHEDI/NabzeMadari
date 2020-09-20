package com.example.pregnancykotlin.models

import com.google.gson.annotations.SerializedName

data class User
    (
    var firstName: String,
    var lastName: String,
    var age: Int,
    var phoneNumber: String,


) {
    var id: Long =0
    var imagePath: String?=null
    @SerializedName("register")
    var isRegister: Boolean = false

}
