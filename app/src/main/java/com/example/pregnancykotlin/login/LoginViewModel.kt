package com.example.pregnancykotlin.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pregnancykotlin.login.remote.Resource
import com.example.pregnancykotlin.models.SmsCode
import com.example.pregnancykotlin.models.TokenInfo
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject


class LoginViewModel @Inject constructor() : ViewModel() {
    private val loginRepository = LoginRepository()
    private var compositeDisposable = CompositeDisposable()

    fun login(phoneNumber: String): MutableLiveData<Resource<TokenInfo>> {
        var result: MutableLiveData<Resource<TokenInfo>> = MutableLiveData()
        result.value = Resource.loading()
        loginRepository.login(phoneNumber).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TokenInfo> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(tokenInfo: TokenInfo) {

                    result.value = Resource.success(tokenInfo)
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        Log.d("kkk", "onError: ${e.code()}")
                    }
                    result.value = Resource.error(e.message)
                }
            })
        return result
    }

    fun generateCode(phoneNumber: String): MutableLiveData<Resource<SmsCode>> {
        var result: MutableLiveData<Resource<SmsCode>> = MutableLiveData()
        result.value = Resource.loading()
        loginRepository.generateCode(phoneNumber).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<SmsCode> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: SmsCode) {
                    result.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
                    result.value = Resource.error(e.message)
                }
            })
        return result
    }
//    fun validateCode(phoneNumber: String,otp :String):MutableLiveData<TokenInfo>{
////        var result:MutableLiveData<TokenInfo>=
//    }
}
