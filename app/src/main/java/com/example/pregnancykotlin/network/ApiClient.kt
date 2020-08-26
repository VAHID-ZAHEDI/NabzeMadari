package com.example.pregnancykotlin.network

import com.example.pregnancykotlin.BuildConfig
import com.example.pregnancykotlin.login.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL: String = BuildConfig.H + BuildConfig.P + "/"

@Module
object ApiClient {
    //    @Module
//    companion object {

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit? {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()
            )
            .build()
//        }

//        @Provides
//        @Singleton
//        fun provideApiService(retrofit: Retrofit): ApiService {
//            return retrofit.create()
//        }
    }

//    private var retrofit: Retrofit? = null
//    val client: Retrofit?
//        get() {
//            if (retrofit == null) {
//                retrofit = Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .baseUrl(BASE_URL)
//                    .build()
//            }
//            return retrofit
//        }


}
