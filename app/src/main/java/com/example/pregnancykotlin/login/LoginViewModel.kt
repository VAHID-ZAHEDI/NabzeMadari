package com.example.pregnancykotlin.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pregnancykotlin.login.remote.Resource
import com.example.pregnancykotlin.models.*
import com.example.pregnancykotlin.network.RetrofitException
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import handleErrorBody
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

//

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
                    result.value = Resource.error(ErrorTest(400, ""))
                }
            })
        return result
    }

    fun validateCode(phoneNumber: String, otp: String): MutableLiveData<Resource<User>> {
        var result: MutableLiveData<Resource<User>> = MutableLiveData()
        result.value = Resource.loading()
        loginRepository.validateCode(phoneNumber, otp).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(object : SingleObserver<User> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: User) {
                    result.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
//                    if (e is HttpException) {
//                        val body = e.response()?.errorBody()
//                        val gson = Gson()
//                        val adapter: TypeAdapter<ErrorTest> =
//                            gson.getAdapter(ErrorTest::class.java)
//                        val errorBody: ErrorTest = adapter.fromJson(body!!.string())
//                        result.value = Resource.error(errorBody)
//                    }
                    result.value = Resource.error(e.handleErrorBody())
                }
            })


        return result
    }

    fun login(phoneNumber: String): MutableLiveData<Resource<TokenInfo>> {
        var result: MutableLiveData<Resource<TokenInfo>> = MutableLiveData()
        result.value = Resource.loading()
        loginRepository.apiLoginDataSource.login(phoneNumber)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<TokenInfo> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: TokenInfo) {
                    result.value = Resource.success(t)

                }

                override fun onError(e: Throwable) {
                    result.value = Resource.error(e.handleErrorBody())
                }
            })
        return result
    }

    fun signUp(user: User): MutableLiveData<Resource<User>> {
        var result: MutableLiveData<Resource<User>> = MutableLiveData()
        result.value = Resource.loading()
        loginRepository.signUp(user).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<User> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: User) {
                    result.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
                    result.value = Resource.error(ErrorTest(400, ""))
                }
            })

        return result
    }

}
