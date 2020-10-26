package com.example.pregnancykotlin.profile

import com.example.pregnancykotlin.models.User
import io.reactivex.Single

interface ProfileDataSource {
    fun getUserInfo(token: String):Single<User>?
}