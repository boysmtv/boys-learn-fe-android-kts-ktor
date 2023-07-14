package com.kotlin.learn.core.data.callback

import androidx.recyclerview.widget.DiffUtil
import com.kotlin.learn.core.model.MovieDataModel

class MovieCallback : DiffUtil.ItemCallback<MovieDataModel>() {
    override fun areItemsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem == newItem
    }
}