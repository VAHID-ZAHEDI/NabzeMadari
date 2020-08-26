package com.example.pregnancykotlin.utilities

import android.util.Base64
import javax.inject.Inject

class Zcript @Inject constructor() {

    fun encrypt(string: String): String {
        return Base64.encodeToString(string.toByteArray(), Base64.DEFAULT)
    }

    fun decrypt(string: String): String {
        return String(Base64.decode(string, Base64.DEFAULT))
    }

}