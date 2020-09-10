package com.example.pregnancykotlin.login.remote

import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.models.ErrorModel

class Resource<T> private constructor(val status: Status, val data: T?, val message: ErrorModel?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> error(errorModel: ErrorModel?): Resource<T> {
            return Resource(Status.ERROR, null, errorModel)
        }
    }
}