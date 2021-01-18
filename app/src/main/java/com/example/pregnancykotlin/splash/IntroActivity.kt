package com.example.pregnancykotlin.splash

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.login.view.LoginActivity
import com.example.pregnancykotlin.utilities.SafePref
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class IntroActivity : AppIntro() {
    var msp: SafePref? = null
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isColorTransitionsEnabled = false
        msp = DaggerPregnancyComponent.builder().setContext(this).build().getSafePref()

        addSlide(
            AppIntroFragment.newInstance(
                title = "به اپلیکیشن خوش‌آمدید",
                description = getString(R.string.intro),
                backgroundColor = Color.parseColor("#673AB7"),
                imageDrawable = R.drawable.ic_logo_intro,
                backgroundDrawable = R.color.red_statusbar,
                titleColor = resources.getColor(R.color.white),
                descriptionColor = resources.getColor(R.color.descriptionColor),
                titleTypefaceFontRes = R.font.vazir

            )
        )
        addSlide(IntroVideoFragment())
        setTransformer(
            AppIntroPageTransformerType.Parallax(
                titleParallaxFactor = -3.0,
                imageParallaxFactor = -1.0,
                descriptionParallaxFactor = -2.0
            )
        )

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        msp?.setSharedPreferences(GlobalVariebles.INTRO_SHOW, true)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        msp?.setSharedPreferences(GlobalVariebles.INTRO_SHOW, true)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
