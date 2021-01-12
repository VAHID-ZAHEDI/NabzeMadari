package com.example.pregnancykotlin.main.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.pregnancykotlin.models.Comment

class DiffUtilCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}