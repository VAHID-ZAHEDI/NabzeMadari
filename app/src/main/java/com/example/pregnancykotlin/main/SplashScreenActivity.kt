package com.example.pregnancykotlin.main

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.view.MainActivity
import com.example.pregnancykotlin.splash.IntroActivity
import com.example.pregnancykotlin.utilities.PrefObject
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        tv_app_name.typeface = Typeface.createFromAsset(assets, "mrt_poster.ttf")

//        (animatedNfcLogo.drawable as? AnimatedVectorDrawable)?.start()
        animated_svg_view.start()
        animated_svg_view.rebuildGlyphData()
        var msp = DaggerPregnancyComponent.builder().setContext(this).build().getSafePref()
        val hasSignIn = msp.getSharedPreferences(
            GlobalVariables.HAS_SIGN_IN,
            PrefObject.BOOLEAN
        ) as Boolean
        Handler().postDelayed({
            if (hasSignIn) {
                var profileViewModel =
                    DaggerInstanceComponent.builder().build().getProfileViewModel()
                profileViewModel.getUserInfo(getToken()).observe(this, Observer {
                    when (it.status) {
                        Status.SUCCESS -> {
                            startActivity(
                                Intent(
                                    this@SplashScreenActivity,
                                    MainActivity::class.java
                                )

                            )
                            finish()
                        }
                        Status.ERROR -> {
                            if (it.error?.httpErrorCode == 430) {
                                var loginViewModel =
                                    DaggerInstanceComponent.builder().build().getLoginViewModel()
                                loginViewModel.login(
                                    msp.getSharedPreferences(
                                        GlobalVariables.PHONE_NUMBER,
                                        PrefObject.STRING
                                    ) as String
                                )
                                    .observe(this, Observer {
                                        when (it.status) {
                                            Status.SUCCESS -> {
                                                msp.setSharedPreferences(
                                                    GlobalVariables.TOKEN,
                                                    "Bearer ${it.data?.accessToken}"
                                                )
                                                startActivity(
                                                    Intent(
                                                        this@SplashScreenActivity,
                                                        MainActivity::class.java
                                                    )

                                                )
                                                finish()
                                            }
                                        }

                                    })

                            }
                        }

                    }
                })

            } else {
                startActivity(Intent(this@SplashScreenActivity, IntroActivity::class.java))
                finish()

            }
//
        }, 2500)

    }
}