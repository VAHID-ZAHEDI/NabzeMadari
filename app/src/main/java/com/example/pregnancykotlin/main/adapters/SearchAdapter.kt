package com.example.pregnancykotlin.main.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.main.view.DetailsActivity
import com.example.pregnancykotlin.models.Content
import com.squareup.picasso.Picasso
import gradientColor
import kotlinx.android.synthetic.main.item_header.view.*

class SearchAdapter(var context: Context, var contents: List<Content>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemView.tv_headerTitle.text = contents[position].title
        holder.itemView.cl_header.gradientColor(arrayListOf("#009ffd","#2a2a72"))
        Picasso
            .get()
            .load("${GlobalVariables.FILE_URL}${contents[position].image}")
            .into(holder.itemView.iv_topic)
        var str: String? = ""
//        if (contents[position].description != null) {
//            for (i in contents[position].description.size - 1 downTo 0 step 1) {
//
//                str += "● " + contents[position].description[i] + "\n"
//            }
//        }
        holder.itemView.tv_description.text = str
//        holder.itemView.tv_likeCount.text = contents[position].likeCount.toString()
        holder.itemView.setOnClickListener {
//            it.findNavController().navigate(R.id.action_subHeaderFragment_to_nav_details)
//            val directions =
//                SubTopicFragmentDirections.actionSubHeaderFragmentToDetailsActivity(subtoics[position]._id)
//            it.findNavController().navigate(directions)
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(GlobalVariables.SUB_TOPIC_ID, contents[position]._id)
            context?.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return contents.size
    }
}