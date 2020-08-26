package com.example.pregnancykotlin.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.login.adapters.ViewPagerAdapter
import com.example.pregnancykotlin.models.SmsCode
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        vp_login.isUserInputEnabled = false
        val fragments = arrayOf(PhoneNumberFragment(), ValidateFragment())
        vp_login.adapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        findNavController(R.id.nav_host)
//                                ?.navigate(R.id.validateFragment, args)
    }
}
