package com.example.pregnancykotlin.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pregnancykotlin.login.remote.Resource
import com.example.pregnancykotlin.models.ErrorModel
import com.example.pregnancykotlin.models.ErrorTest
import com.example.pregnancykotlin.models.SmsCode
import com.example.pregnancykotlin.models.TokenInfo
import com.example.pregnancykotlin.network.RetrofitException
import com.google.gson.Gson
import com.google.gson.TypeAdapter
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
                    result.value = Resource.error(ErrorModel("", 400))
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
//                    var gson = DaggerPregnancyComponent.builder().build().getGson()

                    result.value = Resource.error(ErrorModel("", 400))
                }
            })
        return result
    }

    fun validateCode(phoneNumber: String, otp: String): MutableLiveData<Resource<TokenInfo>> {
        var result: MutableLiveData<Resource<TokenInfo>> = MutableLiveData()
        result.value = Resource.loading()
        loginRepository.validateCode(phoneNumber, otp).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(object : SingleObserver<TokenInfo> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: TokenInfo) {
                    result.value = Resource.success(t)
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val body = e.response()?.errorBody()
                        val gson = Gson()
                        val adapter: TypeAdapter<ErrorTest> =
                            gson.getAdapter(ErrorTest::class.java)
                        val errorBody: ErrorTest = adapter.fromJson(body!!.string())

                        Log.d("mehdi", "onError: ${errorBody.message}")
                    }
                    if(e is RetrofitException){
                        val body = e.getErrorData()
                        var gson = Gson()
                        val error = gson.fromJson(body.toString(), ErrorModel::class.java)
//                        val errorBody = gson.fromJson(body.toString(), ErrorModel::class.java)
                        Log.d("ddd", "onError: ${error.message}")
                    }
                }
            })
//        compositeDisposable.add(
//            loginRepository.apiLoginDataSource.validateCode(phoneNumber, otp)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe({ response -> onResponse(response) }, { t -> onFailure(t) }))

        return result
    }

    private fun onFailure(t: Throwable) {
        if (t is HttpException) {
            val body = t.response()?.errorBody()
//            var gson = DaggerInstanceComponent.create().getGson()
//            val errorBody = gson.fromJson(body.toString(), ErrorModel::class.java)
            Log.d("ddd", "onError: ${t.response()?.errorBody().toString()}")
        }
    }

    private fun onResponse(response: TokenInfo) {
    }
}
