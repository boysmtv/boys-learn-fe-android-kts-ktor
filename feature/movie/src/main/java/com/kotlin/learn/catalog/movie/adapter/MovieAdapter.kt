package com.kotlin.learn.catalog.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.learn.catalog.core.model.NetworkMovieData
import com.kotlin.learn.catalog.feature.movie.databinding.MovieItemBinding

class MovieAdapter : ListAdapter<NetworkMovieData, MovieAdapter.ViewHolder>(MovieCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: NetworkMovieData){
            binding.movieName.text = data.originalTitle
        }

    }

}

private class MovieCallback : DiffUtil.ItemCallback<NetworkMovieData>() {
    override fun areItemsTheSame(oldItem: NetworkMovieData, newItem: NetworkMovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NetworkMovieData, newItem: NetworkMovieData): Boolean {
        return oldItem == newItem
    }
}