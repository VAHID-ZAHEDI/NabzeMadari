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
import com.example.pregnancykotlin.models.SubTopic
import com.squareup.picasso.Picasso
import gradientColor
import kotlinx.android.synthetic.main.item_header.view.*

class SubTopicAdapter(var subtoics: ArrayList<SubTopic>) :
    RecyclerView.Adapter<SubTopicAdapter.SubTopicViewHolder>() {
    var context: Context? = null

    class SubTopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicViewHolder {
        context = parent.context
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
        return SubTopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubTopicViewHolder, position: Int) {
        holder.itemView.tv_headerTitle.text = subtoics[position].title
        holder.itemView.cl_header.gradientColor(subtoics[position].gradientColor)
        Picasso
            .get()
            .load("${GlobalVariables.FILE_URL}${subtoics[position].imagePath}")
            .into(holder.itemView.iv_topic)
        var str: String? = ""
        if (subtoics[position].description != null) {
            for (i in subtoics[position].description.size - 1 downTo 0 step 1) {

                str += "● " + subtoics[position].description[i] + "\n"
            }
        }
        holder.itemView.tv_description.text = str
        holder.itemView.tv_likeCount.text = subtoics[position].likeCount.toString()
        holder.itemView.setOnClickListener {
//            it.findNavController().navigate(R.id.action_subHeaderFragment_to_nav_details)
//            val directions =
//                SubTopicFragmentDirections.actionSubHeaderFragmentToDetailsActivity(subtoics[position]._id)
//            it.findNavController().navigate(directions)
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(GlobalVariables.SUB_TOPIC_ID, subtoics[position]._id)
            context?.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return subtoics?.size
    }


}