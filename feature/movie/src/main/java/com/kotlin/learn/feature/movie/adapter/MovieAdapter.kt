package com.kotlin.learn.feature.movie.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.util.listener.OnClickMovie
import com.kotlin.learn.core.data.callback.MovieCallback
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.utilities.Constant.BASE_URL_IMAGE_500
import com.kotlin.learn.core.utilities.Constant.FIVE_FLOAT
import com.kotlin.learn.core.utilities.Constant.THIRTY_FLOAT
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieHomeItemBinding

class MovieAdapter(private val onClickMovie: OnClickMovie) :

    PagingDataAdapter<MovieDataModel, MovieAdapter.ViewHolder>(MovieCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieHomeItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClickMovie
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        private val binding: MovieHomeItemBinding,
        private val onClickMovie: OnClickMovie
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDataModel) {
            binding.apply {
                thumbnail.load("$BASE_URL_IMAGE_500${item.posterPath}") {
                    val context = root.context
                    val circularProgressDrawable = CircularProgressDrawable(context).apply {
                        strokeWidth = FIVE_FLOAT
                        centerRadius = THIRTY_FLOAT
                        strokeCap = Paint.Cap.BUTT
                        start()
                    }
                    placeholder(circularProgressDrawable)
                    error(R.drawable.ic_baseline_broken_image_24)
                }
                root.setOnClickListener {
                    onClickMovie(item)
                }
            }
        }
    }
}