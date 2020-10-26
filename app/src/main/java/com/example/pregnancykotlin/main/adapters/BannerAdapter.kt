package com.example.pregnancykotlin.main.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.ShowContentFragmentDirections
import com.example.pregnancykotlin.models.Media
import com.example.pregnancykotlin.models.MediaType
import com.example.pregnancykotlin.models.MyNews
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import retrieveVideoFrameFromVideo
import java.security.cert.Extension


class BannerAdapter(bannerData: ArrayList<Media>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private var bannerData = bannerData

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        when (bannerData[position].mediaType) {
            MediaType.IMAGE -> {
                holder.itemView.iv_play.visibility = View.GONE
                Picasso
                    .get()
                    .load("http://192.168.1.103:5902/files/${bannerData[position].url}")
                    .into(holder.itemView.iv_slider)

            }
            MediaType.VIDEO -> {
                holder.itemView.iv_play.visibility = View.VISIBLE

                holder.itemView.iv_slider.retrieveVideoFrameFromVideo(
                    "http://192.168.1.103:5902/files/${bannerData[position].url}"
                )
                holder.itemView.iv_play.setOnClickListener {
                    val directions =
                        ShowContentFragmentDirections.actionShowContentFragment2ToVideoPlayerFragment(
                            "http://192.168.1.103:5902/files/${bannerData[position].url}"
                        )
                    it.findNavController().navigate(directions)

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return bannerData.size
    }


}