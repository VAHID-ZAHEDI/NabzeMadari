package com.example.pregnancykotlin.main.adapters

import android.R.attr.thumb
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.main.view.ShowContentFragmentDirections
import com.example.pregnancykotlin.models.Media
import com.example.pregnancykotlin.models.MediaType
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_banner.view.*


class BannerAdapter(bannerData: ArrayList<Media>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private var bannerData = bannerData
    private var context: Context? = null
    private var isPaly = false
    private var firstPlay = true
    private val mediaPlayer = MediaPlayer()
    private var length: Int = 0
    private var prePostion = 0
    private  var musicUrl: String?=null

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        context = parent.context
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        when (bannerData[position].mediaType) {
            MediaType.IMAGE -> {
                holder.itemView.lootie.visibility = View.GONE
                holder.itemView.iv_slider.visibility = View.VISIBLE
                holder.itemView.iv_play.visibility = View.GONE
                Picasso
                    .get()
                    .load("${GlobalVariables.FILE_URL}${bannerData[position].url}")
                    .into(holder.itemView.iv_slider)

            }
            MediaType.VIDEO -> {
                holder.itemView.lootie.visibility = View.GONE
                holder.itemView.iv_slider.visibility = View.VISIBLE
                holder.itemView.iv_play.visibility = View.VISIBLE

                val options = RequestOptions().frame(thumb.toLong())
                Glide.with(context!!).load("${GlobalVariables.FILE_URL}${bannerData[position].url}")
                    .apply(options)
                    .into(holder.itemView.iv_slider)
                holder.itemView.iv_play.setOnClickListener {
                    val directions =
                        ShowContentFragmentDirections.actionShowContentFragmentToVideoPlayerActivity(
                            "${GlobalVariables.FILE_URL}${bannerData[position].url}"
                        )
                    it.findNavController().navigate(directions)

                }
            }
            MediaType.MUSIC -> {

                holder.itemView.lootie.visibility = View.VISIBLE
                holder.itemView.iv_slider.visibility = View.GONE
                holder.itemView.iv_play.visibility = View.VISIBLE
                holder.itemView.iv_play.setOnClickListener {
                    if (!mediaPlayer.isPlaying) {
                        holder.itemView.iv_play.setImageDrawable(
                            context?.let { it1 ->
                                AppCompatResources.getDrawable(
                                    it1.applicationContext,
                                    R.drawable.ic_pause
                                )
                            }
                        )
                        playMusic(GlobalVariables.FILE_URL + bannerData[position].url)


                    } else {
                        holder.itemView.iv_play.setImageDrawable(
                            AppCompatResources.getDrawable(
                                context!!,
                                R.drawable.ic_play
                            )
                        )
                        pause()

                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return bannerData.size
    }

    private fun playMusic(url: String) {

        mediaPlayer.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            if (musicUrl != url) {
                mediaPlayer.reset()
                setDataSource(url)
                musicUrl = url
                prepare()
            }
            try {
                start()

            } catch (t: Throwable) {

            }
        }
    }

    private fun resume() {
        Log.d("zzd", "resume: $length")
        mediaPlayer.seekTo(length)
        mediaPlayer.start()

    }

    public fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }

    private fun pause() {
        if (mediaPlayer.isPlaying) {
            length = mediaPlayer.currentPosition
            mediaPlayer.pause()
        }
    }

}