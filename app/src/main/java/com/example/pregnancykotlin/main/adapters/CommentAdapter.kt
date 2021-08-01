package com.example.pregnancykotlin.main.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.models.Comment
import kotlinx.android.synthetic.main.item_comment.view.*


class CommentAdapter : PagedListAdapter<Comment, CommentAdapter.CommentViewHolder>(DiffUtilCallback()) {

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.itemView.tv_comment_text.text = getItem(position)?.text
        holder.itemView.tv_name.text = getItem(position)?.userName
    }

//    companion object {
//        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Comment> =
//            object : DiffUtil.ItemCallback<Comment>() {
//                override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
//                    return oldItem._id == newItem._id
//                }
//
//                override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
//                    return oldItem == newItem
//                }
//            }
//    }
}