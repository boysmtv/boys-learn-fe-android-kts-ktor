package com.kotlin.learn.feature.movie.adapter

import android.annotation.SuppressLint
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
import com.kotlin.learn.core.utilities.extension.convertDateFormat
import com.kotlin.learn.core.utilities.extension.getMonthName
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieSeeAllItemBinding
import java.text.SimpleDateFormat

class SeeAllAdapter(private val onClickMovie: OnClickMovie) :

    PagingDataAdapter<MovieDataModel, SeeAllAdapter.ViewHolder>(MovieCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieSeeAllItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClickMovie
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        private val binding: MovieSeeAllItemBinding,
        private val onClickMovie: OnClickMovie
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(item: MovieDataModel) {
            binding.apply {
                tvSeeAllContentTitle.text = item.originalTitle
                tvSeeAllContentDesc.text = item.overview
                tvSeeAllContentDate.text = "Show time ${item.releaseDate?.convertDateFormat()}"

                ivSeeAllContentMovie.load("${Constant.BASE_URL_IMAGE}${item.backdropPath}") {
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

                item.releaseDate?.let { releaseDate ->
                    tvSeeAllDate.text = SimpleDateFormat("dd").parse(releaseDate)
                        ?.let { date -> SimpleDateFormat("dd").format(date) }

                    tvSeeAllMonth.text = SimpleDateFormat("MM").parse(releaseDate)
                        ?.let { month -> SimpleDateFormat("MM").format(month) }?.toInt()?.getMonthName()
                }

                root.setOnClickListener {
                    onClickMovie(item)
                }
            }
        }
    }
}
