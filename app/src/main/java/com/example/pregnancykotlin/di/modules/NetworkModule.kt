package com.example.pregnancykotlin.di.modules

import com.example.pregnancykotlin.BuildConfig
import com.example.pregnancykotlin.network.RxErrorHandlingCallAdapterFactory
import dagger.Module
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
private  const val BASE_URL: String = BuildConfig.H + BuildConfig.P + "/"

@Module
abstract class NetworkModule {
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
//                    .addInterceptor(ChuckInterceptor(AppConfig.context))
                    .build()
            )
            .build()
    }
}