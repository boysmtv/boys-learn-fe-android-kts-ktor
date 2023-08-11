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
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieSearchItemBinding

class SearchAdapter(private val onClickMovie: OnClickMovie) :

    PagingDataAdapter<MovieDataModel, SearchAdapter.ViewHolder>(MovieCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieSearchItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClickMovie
        )
    }

    inner class ViewHolder(
        private val binding: MovieSearchItemBinding,
        private val onClickMovie: OnClickMovie
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDataModel) {
            binding.apply {
                etSearchTitle.text = item.originalTitle

                ivSearch.load("${Constant.BASE_URL_IMAGE_200}${item.backdropPath}") {
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
                    onClickMovie(item)
                }
            }
        }
    }

}