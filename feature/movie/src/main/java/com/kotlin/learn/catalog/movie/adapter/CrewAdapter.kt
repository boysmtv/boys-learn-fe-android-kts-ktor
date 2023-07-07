package com.kotlin.learn.catalog.movie.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.catalog.core.data.callback.MovieCallback
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.MovieCrewItemBinding

class CrewAdapter() :

    PagingDataAdapter<MovieDataModel, CrewAdapter.ViewHolder>(MovieCallback()) {

    override fun onBindViewHolder(holder: CrewAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewAdapter.ViewHolder {
        return ViewHolder(
            MovieCrewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ViewHolder(
        private val binding: MovieCrewItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDataModel) {
            binding.apply {
                tvName.text = item.originalTitle

                ivThumbnail.load("${Constant.BASE_URL_IMAGE}${item.posterPath}")
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
            }
        }
    }

}