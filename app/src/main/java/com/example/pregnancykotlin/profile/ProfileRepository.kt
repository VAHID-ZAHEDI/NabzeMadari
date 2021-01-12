package com.example.pregnancykotlin.profile

import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.User
import com.example.pregnancykotlin.profile.remote.ApiProfileDataSource
import io.reactivex.Single

class ProfileRepository : ProfileDataSource {
    var apiProfileDataSource = ApiProfileDataSource()
    override fun getUserInfo(token: String): Single<User>? {
        return apiProfileDataSource.getUserInfo(token)
    }

    override fun setUnsetBookmark(token: String, contentId: String): Single<Void>? {
        return apiProfileDataSource.setUnsetBookmark(token, contentId)
    }

    override fun getBookmarkContent(token: String): Single<List<Content>>? {
        return apiProfileDataSource?.getBookmarkContent(token)
    }

}