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
        isColorTransitionsEnabled = true
        msp = DaggerPregnancyComponent.builder().setContext(this).build().getSafePref()

        addSlide(
            AppIntroFragment.newInstance(
                title = "به اپلیکیشن خوش‌آمدید",
                description = "اپلیکیشن آمادگی برای زایمان، طبق دستورالعمل وزارت بهداشت، درمان و آموزش پزشکی و زیر نظر دانشگاه علوم پزشکی ایران طراحی شده و بلافاصله پس از اطلاع از بارداری قابل استفاده میباشد",
                backgroundColor = Color.parseColor("#673AB7"),
                imageDrawable = R.drawable.ic_logo_intro,
                backgroundDrawable = R.color.red_statusbar,
                titleColor = resources.getColor(R.color.white),
                descriptionColor = resources.getColor(R.color.descriptionColor),
                titleTypefaceFontRes = R.font.vazir

            )
        )


        addSlide(
            AppIntroFragment.newInstance(
                backgroundColor = Color.parseColor("#FF5722"),
                title = "COVID-19",
                imageDrawable = R.drawable.ic_covid,
                descriptionColor = resources.getColor(R.color.descriptionColor),
                description = "همچنین اپلیکیشن حاضر دارای آخرین اطلاعات مربوط به اپیدمی COVID-19 در دوران بارداری، زایمان و شیردهی میباشد"
            )
        )
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
