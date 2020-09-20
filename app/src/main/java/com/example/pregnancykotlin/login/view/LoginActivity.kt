package com.example.pregnancykotlin.login.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.R
import io.github.inflationx.viewpump.ViewPumpContextWrapper


class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        vp_login.isUserInputEnabled = false
//        val fragments = arrayOf(PhoneNumberFragment(), ValidateFragment())
//        vp_login.adapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)

    }
}
