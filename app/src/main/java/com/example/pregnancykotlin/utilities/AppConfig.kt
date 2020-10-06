package com.example.pregnancykotlin.utilities

import android.app.Application
import android.content.Context
import android.os.StrictMode
import android.view.LayoutInflater
import com.example.pregnancykotlin.R
import com.readystatesoftware.chuck.Chuck
import com.readystatesoftware.chuck.ChuckInterceptor
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import okhttp3.OkHttpClient


class AppConfig : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("Vazir.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

//        startActivity(Chuck.getLaunchIntent(context))
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    companion object {
        var inflater: LayoutInflater? = null
        var context: Context? = null
    }
}