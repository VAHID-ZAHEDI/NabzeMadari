package com.example.pregnancykotlin.profile

import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.User
import io.reactivex.Single

interface ProfileDataSource {
    fun getUserInfo(token: String): Single<User>?
    fun setUnsetBookmark(token: String, contentId: String): Single<Void>?
    fun getBookmarkContent(token: String): Single<List<Content>>?
}