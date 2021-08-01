package com.example.pregnancykotlin.splash

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pregnancykotlin.GlobalVariables
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
        player.setSource(Uri.parse("${GlobalVariables.FILE_URL}Introduction.mp4"))
        player.setAutoPlay(true)
    }

    companion object {
        fun newInstance(): IntroVideoFragment {
            return IntroVideoFragment()
        }
    }
}