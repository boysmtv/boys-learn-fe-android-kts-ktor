package com.kotlin.learn.catalog.movie.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.utilities.Constant.BASE_URL_IMAGE
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.MovieItemBinding

typealias OnClickPopularMovie = (MovieDataModel, MovieCategories) -> Unit

class MovieAdapter(
    private val onClickPopularMovie: OnClickPopularMovie,
    private val categories: MovieCategories
) :
    PagingDataAdapter<MovieDataModel, MovieAdapter.ViewHolder>(MovieCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClickPopularMovie
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        private val binding: MovieItemBinding,
        private val onClickPopularMovie: OnClickPopularMovie
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDataModel) {
            binding.apply {
                thumbnail.load("$BASE_URL_IMAGE${item.posterPath}")
                {
                    val context = root.context
                    val circularProgressDrawable = CircularProgressDrawable(context).apply {
                        strokeWidth = 5f
                        centerRadius = 30f
                        strokeCap = Paint.Cap.BUTT
                        start()
                    }
                    placeholder(circularProgressDrawable)
                    error(R.drawable.ic_baseline_broken_image_24)
                }
                root.setOnClickListener {
                    onClickPopularMovie(item, categories)
                }
            }
        }
    }
}

private class MovieCallback : DiffUtil.ItemCallback<MovieDataModel>() {
    override fun areItemsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDataModel, newItem: MovieDataModel): Boolean {
        return oldItem == newItem
    }
}