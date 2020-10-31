package com.example.pregnancykotlin.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.login.view.LoginActivity
import com.example.pregnancykotlin.main.view.MainActivity
import com.example.pregnancykotlin.utilities.PrefObject

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var hasSignIn =
            DaggerPregnancyComponent.builder().setContext(this).build().getSafePref()
                .getSharedPreferences(
                    GlobalVariebles.HAS_SIGN_IN,
                    PrefObject.BOOLEAN
                ) as Boolean
        Handler().postDelayed({
            if (hasSignIn) {

                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))

            } else {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))

            }
            finish()

        }, 5000)
    }
}