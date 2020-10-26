package com.example.pregnancykotlin.profile

import com.example.pregnancykotlin.models.User
import com.example.pregnancykotlin.profile.remote.ApiProfileDataSource
import io.reactivex.Single

class ProfileRepository : ProfileDataSource {
    var apiProfileDataSource = ApiProfileDataSource()
    override fun getUserInfo(token: String): Single<User>? {
        return apiProfileDataSource.getUserInfo(token)
    }

}