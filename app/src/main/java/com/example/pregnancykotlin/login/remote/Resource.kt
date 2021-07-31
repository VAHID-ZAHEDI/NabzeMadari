package com.example.pregnancykotlin.login.remote

import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.models.ErrorModel
import com.example.pregnancykotlin.models.ErrorTest

class Resource<T> private constructor(val status: Status, val data: T?, val error: ErrorTest?) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> error(errorModel: ErrorTest?): Resource<T> {
            return Resource(Status.ERROR, null, errorModel)
        }
    }
}