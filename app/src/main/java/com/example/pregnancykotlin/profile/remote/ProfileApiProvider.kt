package com.example.pregnancykotlin.profile.remote

import com.example.pregnancykotlin.network.ApiClient

class ProfileApiProvider {
    companion object {
        var profileApiServices: ProfileApiServices? = null
        fun profileApiService(): ProfileApiServices? {
            if (profileApiServices == null) {
                profileApiServices =
                    ApiClient.provideRetrofit()?.create(ProfileApiServices::class.java)

            }
            return profileApiServices
        }
    }
}