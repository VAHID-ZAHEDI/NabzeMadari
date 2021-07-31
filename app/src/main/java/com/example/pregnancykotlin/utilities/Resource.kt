package com.example.pregnancykotlin.utilities

import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.models.Errors

class Resource<T> private constructor(val status: Status, val data: T?, val error: Errors?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> error(errorModel: Errors?): Resource<T> {
            return Resource(Status.ERROR, null, errorModel)
        }
    }
}