package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.erkutaras.showcaseview.ShowcaseManager
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView





class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar(toolbar, false)
        findNavController(R.id.nav_host_fragment)
            .setGraph(R.navigation.nav_main, intent.extras)
        if (savedInstanceState == null)
            setupBottomNavigationBar()
        MaterialShowcaseView.Builder(this)
            .setTarget(findViewById(R.id.calculatorFragment))
            .setDismissText("متوجه شدم")
            .setContentText("شما در این قسمت میتوانید bmi ،تاریخ تقریبی زایمان و سن بارداری خود را محاسبه کنید.")
            .setDelay(500)
            .useFadeAnimation()// optional but starting animations immediately in onCreate can make them choppy
            .singleUse("calculator") // provide a unique ID used to ensure it is only shown once
            .show()

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()

    }

    private fun setupBottomNavigationBar() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_nav, navController)
    }


//    override fun onSupportNavigateUp(): Boolean {
//        return currentNavController?.value?.navigateUp() ?: false
//    }

    private var listener = { v: View ->
        val builder = ShowcaseManager.Builder()
        builder.context(this)
            .view(v)
            .descriptionImageRes(R.mipmap.ic_launcher_round)
            .descriptionTitle("LOREM IPSUM DOLOR")
            .descriptionText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
            .buttonText("DONE")
            .key("TEST")
            .developerMode(true)
            .marginFocusArea(0)
            .gradientFocusEnabled(true)
            .add().build()
            .show()
    }

}