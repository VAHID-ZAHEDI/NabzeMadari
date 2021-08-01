package com.example.pregnancykotlin.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pregnancykotlin.utilities.Resource
import com.example.pregnancykotlin.models.Content
import com.example.pregnancykotlin.models.Errors
import com.example.pregnancykotlin.models.User
import handleErrorBody
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    var profileRepository = ProfileRepository()
    private val compositeDisposable = CompositeDisposable()
    var userLiveData: MutableLiveData<Resource<User>> = MutableLiveData()

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
                    user.value = Resource.error(Errors(430, e.message.toString()))
                }
            })

        return user
    }

    fun setUnsetBookmark(token: String, contentId: String): MutableLiveData<Resource<Void>> {
        var result: MutableLiveData<Resource<Void>> = MutableLiveData()
        result.value = Resource.loading()
        profileRepository.apiProfileDataSource.setUnsetBookmark(token, contentId)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<Void> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Void) {
                    result.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
                    result.value = Resource.error(e.handleErrorBody())
                }
            })

        return result
    }

    fun getBookmarkContent(token: String): MutableLiveData<Resource<List<Content>>> {
        var result: MutableLiveData<Resource<List<Content>>> = MutableLiveData()
        result.value = Resource.loading()
        profileRepository.apiProfileDataSource.getBookmarkContent(token)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<List<Content>> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Content>) {
                    result.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
                    result.value = Resource.error(e.handleErrorBody())
                }
            })
        return result
    }

    fun updateUserInfo(
        token: String,
        firstName: String,
        lastName: String,
        height: Int,
        weight: Int
    ): MutableLiveData<Resource<User>> {
        userLiveData.value = Resource.loading()
        profileRepository.updateUserInfo(token, firstName, lastName, height, weight)
            ?.subscribeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe(object : SingleObserver<User> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: User) {
                    userLiveData.postValue( Resource.success(t))
                }

                override fun onError(e: Throwable) {
                    userLiveData.value = Resource.error(e.handleErrorBody())
                }
            })

        return userLiveData
    }


}