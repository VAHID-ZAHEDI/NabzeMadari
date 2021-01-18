package com.example.pregnancykotlin.models

import com.google.gson.annotations.SerializedName
data class User
    (

    var firstName: String,
    var lastName: String,
    var age: Int,
    var phoneNumber: String,
    var weight: Int,
    var height: Int


) {
    var _id: String? = null
    var imagePath: String? = null

    @SerializedName("register")
    var isRegister: Boolean = false

}
