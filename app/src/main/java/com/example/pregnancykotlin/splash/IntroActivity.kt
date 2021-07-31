package com.example.pregnancykotlin.splash

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.login.view.LoginActivity
import com.example.pregnancykotlin.utilities.SafePref
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import io.github.inflationx.viewpump.ViewPumpContextWrapper

class IntroActivity : AppIntro() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isColorTransitionsEnabled = false

        addSlide(
            AppIntroFragment.newInstance(
                title = "به نبض مادری خوش‌آمدید",
                description = getString(R.string.intro),
                backgroundColor = Color.parseColor("#673AB7"),
                imageDrawable = R.drawable.ic_logo_intro,
                backgroundDrawable = R.color.red_statusbar,
                titleColor = resources.getColor(R.color.white),
                descriptionColor = resources.getColor(R.color.descriptionColor),
                titleTypefaceFontRes = R.font.vazir

            )
        )
        setTransformer(
            AppIntroPageTransformerType.Parallax(
                titleParallaxFactor = -3.0,
                imageParallaxFactor = -1.0,
                descriptionParallaxFactor = -2.0
            )
        )
        addSlide(IntroVideoFragment.newInstance())


    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
