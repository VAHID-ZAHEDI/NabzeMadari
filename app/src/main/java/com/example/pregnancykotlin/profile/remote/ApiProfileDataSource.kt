package com.example.pregnancykotlin.profile.remote

import com.example.pregnancykotlin.models.User
import com.example.pregnancykotlin.profile.ProfileDataSource
import io.reactivex.Single

class ApiProfileDataSource :ProfileDataSource{
    var profileApiServices=ProfileApiProvider.profileApiService()
    override fun getUserInfo(token: String) : Single<User>? {
        return profileApiServices?.getUserInfo(token)
    }

}