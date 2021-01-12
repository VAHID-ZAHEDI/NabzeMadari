package com.example.pregnancykotlin.main.adapters

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.pregnancykotlin.main.MainRepository
import com.example.pregnancykotlin.models.Comment
import com.example.pregnancykotlin.models.CommentsPaging
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CommentPagingDataSource(
    var token: String,
    var contentId: String,

    ) : PageKeyedDataSource<Int, Comment>() {

    var mainRepository: MainRepository = MainRepository()
    private val compositeDisposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Comment>
    ) {
        Log.d("ytyty", "loadInitial: SALAM SALAM")
        mainRepository.getContentComments(token, contentId, 0, 4)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<CommentsPaging> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: CommentsPaging) {
                    Log.d("yuu", "onSuccess: ${t.content?.size}")
                    Log.d("yuu", "onSuccess: ${t.last}")
                    if (t.content?.size != 0) {
                        callback.onResult(
                            t?.content as MutableList<Comment>,
                            null,
                            1
                        )
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("zzzmmm", "ERROR: ${e.message}")

                }
            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        Log.d("bhbh", "loadAfter: ${params.key}")

        mainRepository.getContentComments(token, contentId, params.key, 4)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<CommentsPaging> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: CommentsPaging) {
                    if (t.content?.size != 0) {
                        callback.onResult(
                            t?.content as MutableList<Comment>,
                            params.key + 1
                        )
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("zzzmmm", "ERROR: ${e.message}")

                }
            })
    }


}