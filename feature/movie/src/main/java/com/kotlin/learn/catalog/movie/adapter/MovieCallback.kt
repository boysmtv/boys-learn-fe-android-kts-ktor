package com.kotlin.learn.catalog.movie.adapter

import androidx.recyclerview.widget.DiffUtil
import com.kotlin.learn.catalog.core.model.MovieDataModel

class MovieCallback : DiffUtil.ItemCallback<MovieDataModel>() {
    override fun areItemsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem == newItem
    }
}