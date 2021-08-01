package com.example.pregnancykotlin.profile.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pregnancykotlin.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_communication.*
import javax.inject.Inject


class CommunicationFragment @Inject constructor() : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_communication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_telegram.setOnClickListener {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/MousaviR"))
            startActivity(telegram)
        }
    }

}