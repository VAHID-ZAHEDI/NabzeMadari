package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.R

class DetailsActivity : BaseActivity() {
    private val args: DetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        Log.d("vahid", "onCreate: ${args.subTopicId}")

        Navigation.findNavController(this,R.id.details).setGraph(R.navigation.nav_details, intent.extras)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)

    }
}