package com.example.pregnancykotlin.main.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.models.MyNews
import com.squareup.picasso.Picasso
import gradientColor
import kotlinx.android.synthetic.main.item_home_banner.view.*

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
            .load("${GlobalVariables.FILE_URL}${myNewsList[position].imagePath}")
            .into(holder.itemView.iv_slider)
        holder.itemView.ll_title.gradientColor(arrayListOf("#5F0A87", "#A4508B"))
        Log.d("jcjcj", "${GlobalVariables.FILE_URL}/${myNewsList[position].imagePath}")
    }

    override fun getItemCount(): Int {
        return myNewsList.size
    }
}