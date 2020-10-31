package com.example.pregnancykotlin.main.adapters

import android.R.attr.thumb
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.pregnancykotlin.GlobalVariebles
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.ShowContentFragmentDirections
import com.example.pregnancykotlin.models.Media
import com.example.pregnancykotlin.models.MediaType
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_banner.view.*


class BannerAdapter(bannerData: ArrayList<Media>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private var bannerData = bannerData
    private var context: Context? = null

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
                holder.itemView.iv_play.visibility = View.GONE
                Picasso
                    .get()
                    .load("${GlobalVariebles.FILE_URL}${bannerData[position].url}")
                    .into(holder.itemView.iv_slider)

            }
            MediaType.VIDEO -> {
                holder.itemView.iv_play.visibility = View.VISIBLE

//                holder.itemView.iv_slider.retrieveVideoFrameFromVideo(
//                    "${GlobalVariebles.FILE_URL}${bannerData[position].url}"
//
//                )

//                Glide.with(context!!)
//                    .asBitmap()
//                    .load("${GlobalVariebles.FILE_URL}${bannerData[position].url}")
//                    .diskCacheStrategy(DiskCacheStrategy.DATA)
//                    .into(holder.itemView.iv_slider)
                val options = RequestOptions().frame(thumb.toLong())
                Glide.with(context!!).load("${GlobalVariebles.FILE_URL}${bannerData[position].url}")
                    .apply(options)
                    .into(holder.itemView.iv_slider)
                holder.itemView.iv_play.setOnClickListener {
                    val directions =
                        ShowContentFragmentDirections.actionShowContentFragmentToVideoPlayerActivity(
                            "${GlobalVariebles.FILE_URL}${bannerData[position].url}"
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