package com.example.pregnancykotlin.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.models.MyNews
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_banner.view.*
import kotlinx.android.synthetic.main.item_home_banner.view.iv_slider

class HomeBannerAdapter(var myNewsList: ArrayList<MyNews>) :
    RecyclerView.Adapter<HomeBannerAdapter.HomeBannerViewHolder>() {
    class HomeBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)
        return HomeBannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.itemView.tv_newsTitle.text = myNewsList[position].title
        Picasso
            .get()
            .load("http://192.168.1.103:5902/files/${myNewsList[position].imagePath}")
            .into(holder.itemView.iv_slider)
    }

    override fun getItemCount(): Int {
        return myNewsList.size
    }
}