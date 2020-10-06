package com.example.pregnancykotlin.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pregnancykotlin.login.remote.Resource
import com.example.pregnancykotlin.models.ErrorTest
import com.example.pregnancykotlin.models.SubTopic
import com.example.pregnancykotlin.models.Topic
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    private val mainRepository = MainRepository()
    private val compositeDisposable = CompositeDisposable()
    fun getAllTopics(token: String): MutableLiveData<Resource<List<Topic>>> {
        var result: MutableLiveData<Resource<List<Topic>>> = MutableLiveData()
        result.value = Resource.loading()
        Log.d("myToken", "getAllTopics: $token")
        mainRepository.apiMainDataSource.getAllTopic(token)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<List<Topic>> {
                override fun onSuccess(topics: List<Topic>) {
                    result.value = Resource.success(topics)
                    Log.d("myTopic", "onViewCreated: ${topics.size}")

                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    Log.d("myTopic", "ERROR: ${e.message}")
                    result.value = Resource.error(ErrorTest(400, ""))
                }
            })
        return result
    }

    fun getSubTopicsById(
        token: String,
        topicId: String
    ): MutableLiveData<Resource<ArrayList<SubTopic>>> {
        var result: MutableLiveData<Resource<ArrayList<SubTopic>>> = MutableLiveData()
        result.value = Resource.loading()
        mainRepository.apiMainDataSource.getSubTopicsById(token, topicId)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<ArrayList<SubTopic>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: ArrayList<SubTopic>) {
                    result.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
                    result.value = Resource.error(ErrorTest(400, e.message.toString()))
                }
            })

        return result
    }
}