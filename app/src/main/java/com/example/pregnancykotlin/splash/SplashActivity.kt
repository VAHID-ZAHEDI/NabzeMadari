package com.example.pregnancykotlin.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.utilities.PrefObject
import com.readystatesoftware.chuck.internal.ui.MainActivity
import java.util.*

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var hasSignIn: Boolean
        var msp = DaggerPregnancyComponent.builder().setContext(this).build().getSafePref()
        hasSignIn = msp.getSharedPreferences(
            GlobalVariables.HAS_SIGN_IN,
            PrefObject.BOOLEAN
        ) as Boolean
        Handler().postDelayed({
            if (hasSignIn) {

                startActivity(Intent(this@SplashActivity, MainActivity::class.java))

            } else {

                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))

            }
            finish()

        }, 5000)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                Log.d("aaaaaaa", "run: ")
                Toast.makeText(this@SplashActivity, "www", Toast.LENGTH_SHORT).show()
            }
        }, 2000)
//        fixedRateTimer("timer", false, 0, 5000) {
//
//
//        }


    }
}