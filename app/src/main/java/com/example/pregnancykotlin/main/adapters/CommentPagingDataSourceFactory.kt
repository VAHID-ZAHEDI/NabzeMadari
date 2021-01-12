package com.example.pregnancykotlin.main.adapters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.pregnancykotlin.models.Comment


class CommentPagingDataSourceFactory(
    var token: String,
    var contentId: String,
) : DataSource.Factory<Int, Comment>() {


    private val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Comment>>()


    override fun create(): DataSource<Int, Comment> {
        val commentPagingDataSource = CommentPagingDataSource(token,contentId)
        itemLiveDataSource.postValue(commentPagingDataSource)
        return commentPagingDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, Comment>>? {
        return itemLiveDataSource
    }
}