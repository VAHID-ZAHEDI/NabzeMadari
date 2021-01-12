package com.example.pregnancykotlin.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.login.view.LoginActivity
import com.example.pregnancykotlin.main.view.MainActivity
import com.example.pregnancykotlin.splash.IntroActivity
import com.example.pregnancykotlin.utilities.PrefObject
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//        (animatedNfcLogo.drawable as? AnimatedVectorDrawable)?.start()
        animated_svg_view.start()
        animated_svg_view.rebuildGlyphData()
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
                startActivity(Intent(this@SplashScreenActivity, IntroActivity::class.java))

            }
            finish()

        }, 5000)
    }
}