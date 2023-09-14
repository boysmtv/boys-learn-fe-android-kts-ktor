package com.kotlin.learn.feature.movie.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.util.listener.OnClickRecent
import com.kotlin.learn.core.model.RecentDataModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieSearchItemBinding

class RecentAdapter(private val clickMovie: OnClickRecent) :

    RecyclerView.Adapter<RecentAdapter.ViewHolder>() {

    private var listFavourite = mutableListOf<RecentDataModel>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFavourite[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieSearchItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), clickMovie
        )
    }

    override fun getItemCount(): Int {
        return listFavourite.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(favourite: MutableList<RecentDataModel>) {
        listFavourite = favourite
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private var binding: MovieSearchItemBinding,
        private val onClickMovie: OnClickRecent
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecentDataModel) {
            binding.apply {
                tvTitle.text = item.originalTitle

                thumbnail.load("${Constant.BASE_URL_IMAGE_200}${item.posterPath}") {
                    val context = root.context
                    val circularProgressDrawable = CircularProgressDrawable(context).apply {
                        strokeWidth = 5f
                        centerRadius = 30f
                        strokeCap = android.graphics.Paint.Cap.BUTT
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