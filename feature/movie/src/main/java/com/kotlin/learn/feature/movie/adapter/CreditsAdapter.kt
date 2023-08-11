package com.kotlin.learn.feature.movie.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.model.CastModel
import com.kotlin.learn.core.model.CrewModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieCreditsItemBinding

sealed class CreditsAdapter {

    class Cast :

        ListAdapter<CastModel, Cast.ViewHolder>(CastCallback()) {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            item?.let { holder.bind(it) }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                MovieCreditsItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        inner class ViewHolder(
            private val binding: MovieCreditsItemBinding,
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: CastModel) {
                binding.apply {
                    tvName.text = item.name

                    ivThumbnail.load("${Constant.BASE_URL_IMAGE_500}${item.profilePath}")
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

        class CastCallback : DiffUtil.ItemCallback<CastModel>() {
            override fun areItemsTheSame(oldItem: CastModel, newItem: CastModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CastModel, newItem: CastModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    class Crew :

        ListAdapter<CrewModel, Crew.ViewHolder>(CrewCallback()) {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            item?.let { holder.bind(it) }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                MovieCreditsItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        inner class ViewHolder(
            private val binding: MovieCreditsItemBinding,
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: CrewModel) {
                binding.apply {
                    tvName.text = item.name

                    ivThumbnail.load("${Constant.BASE_URL_IMAGE_500}${item.profilePath}") {
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

        class CrewCallback : DiffUtil.ItemCallback<CrewModel>() {
            override fun areItemsTheSame(oldItem: CrewModel, newItem: CrewModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CrewModel, newItem: CrewModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}

