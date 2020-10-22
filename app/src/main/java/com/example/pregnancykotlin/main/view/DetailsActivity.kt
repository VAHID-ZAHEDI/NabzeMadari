package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.R
import kotlinx.android.synthetic.main.fragment_show_content.*

class DetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        findNavController(R.id.details)
            .setGraph(R.navigation.nav_details, intent.extras)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)

    }
}