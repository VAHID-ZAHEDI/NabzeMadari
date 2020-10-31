package com.example.pregnancykotlin.main.view

import android.net.Uri
import android.os.Bundle
import androidx.navigation.navArgs
import com.example.pregnancykotlin.BaseActivity
import com.example.pregnancykotlin.R
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : BaseActivity() {
    private val args: VideoPlayerActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        player.setSource(Uri.parse(args.videoUrl))
        player.setAutoPlay(true)


    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }
}