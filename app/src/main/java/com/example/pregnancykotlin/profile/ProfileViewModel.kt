package com.example.pregnancykotlin.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pregnancykotlin.login.remote.Resource
import com.example.pregnancykotlin.models.ErrorTest
import com.example.pregnancykotlin.models.User
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    var profileRepository = ProfileRepository()
    private val compositeDisposable = CompositeDisposable()

    fun getUserInfo(token: String): MutableLiveData<Resource<User>> {
        var user: MutableLiveData<Resource<User>> = MutableLiveData()
        user.value = Resource.loading()
        profileRepository.getUserInfo(token)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<User> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: User) {
                    user.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
                    user.value = Resource.error(ErrorTest(430, e.message.toString()))
                }
            })

        return user
    }

}