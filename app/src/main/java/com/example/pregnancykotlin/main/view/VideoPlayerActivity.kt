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
//        player.setSource(Uri.parse("https://dls.music-fa.com/tagdl/99/Mohsen%20Yeganeh%20-%20Remix%20Behet%20Ghol%20Midam%20(128).mp3"))
        player.setAutoPlay(true)


    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }
}