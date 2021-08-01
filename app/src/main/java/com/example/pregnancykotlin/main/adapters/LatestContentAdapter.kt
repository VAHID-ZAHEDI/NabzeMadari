package com.example.pregnancykotlin.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.main.view.MainFragmentDirections
import com.example.pregnancykotlin.main.view.ProfileFragmentDirections
import com.example.pregnancykotlin.models.Content
import gradientColor
import kotlinx.android.synthetic.main.item_content_home.view.*

class LatestContentAdapter(var contents: ArrayList<Content>, var type: ContentFrom) :
    RecyclerView.Adapter<LatestContentAdapter.LatestContentViewHolder>() {
    var context: Context? = null

    enum class ContentFrom { PROFILE, LATEST }

    class LatestContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestContentViewHolder {
        context = parent.context
        return LatestContentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_content_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LatestContentViewHolder, position: Int) {
        holder.itemView.tv_title.text = contents[position].title
        val options = RequestOptions().frame(android.R.attr.thumb.toLong())
        Glide.with(context!!)
            .load("${GlobalVariables.FILE_URL}${contents[position].image}")
            .apply(options)
            .into(holder.itemView.iv_content)
        holder.itemView.ll_title.gradientColor(arrayListOf("#009FFD", "#2A2A72"))
        holder.itemView.setOnClickListener {

            val direction: NavDirections = if (ContentFrom.LATEST == type) {
                MainFragmentDirections.actionMainFragmentToDetailsActivity(
                    contents[position].subTopicId
                )
            } else {

                ProfileFragmentDirections.actionProfileFragmentToDetailsActivity(contents[position].subTopicId)
            }

            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return contents.size
    }
}