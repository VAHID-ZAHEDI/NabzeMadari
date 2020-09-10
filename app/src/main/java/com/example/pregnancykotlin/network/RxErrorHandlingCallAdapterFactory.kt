package com.example.pregnancykotlin.network

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type

class RxErrorHandlingCallAdapterFactory: CallAdapter.Factory() {

    private val _original by lazy {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    companion object {
        fun create() : CallAdapter.Factory = RxErrorHandlingCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        val wrapped = _original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
        return RxCallAdapterWrapper(retrofit, wrapped)
    }

    private class RxCallAdapterWrapper<R>(val _retrofit: Retrofit,
                                          val _wrappedCallAdapter: CallAdapter<R, *>
    ): CallAdapter<R, Single<R>> {

        override fun responseType(): Type = _wrappedCallAdapter.responseType()


        @SuppressLint("CheckResult")
        @Suppress("UNCHECKED_CAST")
        override fun adapt(call: Call<R>): Single<R> {
            val adapted = (_wrappedCallAdapter.adapt(call) as Single<R>)
            adapted.onErrorResumeNext { throwable: Throwable ->
                Single.error(asRetrofitException(throwable))

            }

            return adapted
        }

        private fun asRetrofitException(throwable: Throwable): RetrofitException {
            Log.d("zzz", "asRetrofitException: ")
            // We had non-200 http error
            if (throwable is HttpException) {
                Log.d("zzz", "asRetrofitException: ${throwable.code()}")
                val response = throwable.response() as Response<*>
                if (throwable.code() == 400) {
                    // on out api 422's get metadata in the response. Adjust logic here based on your needs
                    return RetrofitException.httpErrorWithObject(response?.raw()?.request?.url.toString(), response, _retrofit)
                } else {
                    return RetrofitException.httpError(response?.raw()?.request?.url.toString(), response, _retrofit)
                }
            }

            // A network error happened
            if (throwable is IOException) {
                Log.d("zzz", "asRetrofitException: ${throwable.message}")

                return RetrofitException.networkError(throwable)
            }

            // We don't know what happened. We need to simply convert to an unknown error
            Log.d("zzz", "vvv: ${throwable.message}")

            return RetrofitException.unexpectedError(throwable)
        }

    }
}