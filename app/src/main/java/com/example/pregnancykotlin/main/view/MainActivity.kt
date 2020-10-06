package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            setupBottomNavigationBar()

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

}