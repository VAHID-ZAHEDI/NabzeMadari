package com.example.pregnancykotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.utilities.Dialogs
import com.example.pregnancykotlin.utilities.PrefObject
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

    fun initToolbar(toolbar:Toolbar,showBackButton:Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton);

    }
    fun getToken(): String {
        return DaggerPregnancyComponent.builder().setContext(this as Context).build()
            .getSafePref().getSharedPreferences(GlobalVariables.TOKEN, PrefObject.STRING) as String
    }

}