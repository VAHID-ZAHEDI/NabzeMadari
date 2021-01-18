package com.example.pregnancykotlin.splash

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import kotlinx.android.synthetic.main.activity_video_player.*

class IntroVideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player.setSource(Uri.parse("${GlobalVariebles.FILE_URL}Introduction.mp4"))
        player.setAutoPlay(true)
    }

}