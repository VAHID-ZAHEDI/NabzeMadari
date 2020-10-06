package com.example.pregnancykotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pregnancykotlin.utilities.Dialogs
import io.github.inflationx.viewpump.ViewPumpContextWrapper

open class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showLoadingDialog() {
        Dialogs.showLoadingDialog(this)
    }

    fun dismissLoadingDialog() {

        Dialogs.dismissLoadingDialog()
    }
}