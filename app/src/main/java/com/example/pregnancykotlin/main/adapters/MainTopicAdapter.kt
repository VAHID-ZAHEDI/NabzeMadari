package com.example.pregnancykotlin.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.main.view.CategoryFragment
import com.example.pregnancykotlin.main.view.CategoryFragmentDirections
import com.example.pregnancykotlin.models.Topic
import com.squareup.picasso.Picasso
import gradientColor
import kotlinx.android.synthetic.main.item_header.view.*

class MainTopicAdapter(var headers: List<Topic>) :
    RecyclerView.Adapter<MainTopicAdapter.HeaderViewHolder>() {
    private lateinit var context: Context

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
        context = parent.context
        return HeaderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return headers.size
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.itemView.tv_headerTitle.text = headers[position].title
        var colors = headers[position].gradientColor
        holder.itemView.cl_header.gradientColor(colors)
        Picasso
            .get()
            .load("http://192.168.1.103:5902/files/${headers[position].imagePath}")
            .into(holder.itemView.iv_topic)
        var str: String? = ""
        for (i in headers[position].description.size - 1 downTo 0 step 1) {

            str += "● " + headers[position].description[i] + "\n"
        }
        holder.itemView.tv_description.text = str
        holder.itemView.setOnClickListener {
            val action =
                CategoryFragmentDirections.actionCategoryFragmentToSubHeaderFragment(headers[position]._id)
            Navigation.findNavController(holder.itemView).navigate(action)

        }
    }
}