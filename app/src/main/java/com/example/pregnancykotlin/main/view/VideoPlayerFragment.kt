package com.example.pregnancykotlin.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.pregnancykotlin.R
import hb.xvideoplayer.MxVideoPlayer
import hb.xvideoplayer.MxVideoPlayerWidget
import kotlinx.android.synthetic.main.fragment_video_player.*


class VideoPlayerFragment : Fragment() {
    val args: VideoPlayerFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        mpw_video_player.startPlay(
//            args.videoUrl,
//            MxVideoPlayer.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//        )
//        MxVideoPlayerWidget.startFullscreen(
//            activity,
//            MxVideoPlayerWidget::class.java, args.videoUrl, "video name"
//        )
        andExoPlayerView.setSource(args.videoUrl)


    }

}