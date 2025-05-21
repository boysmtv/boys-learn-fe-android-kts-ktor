package com.kotlin.learn.feature.movie.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.util.listener.OnClickCreditsCast
import com.kotlin.learn.core.common.util.listener.OnClickCreditsCrew
import com.kotlin.learn.core.model.CastModel
import com.kotlin.learn.core.model.CrewModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.FIVE_FLOAT
import com.kotlin.learn.core.utilities.Constant.THIRTY_FLOAT
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieCreditsItemBinding
import com.kotlin.learn.feature.movie.databinding.SeeAllCreditsItemBinding

sealed class SeeAllCreditsAdapter {

    class Cast(private val onClickCreditsCast: OnClickCreditsCast) :

        ListAdapter<CastModel, Cast.ViewHolder>(CastCallback()) {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            item?.let { holder.bind(it) }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                SeeAllCreditsItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), onClickCreditsCast
            )
        }

        inner class ViewHolder(
            private val binding: SeeAllCreditsItemBinding,
            private val onClickCreditsCast: OnClickCreditsCast
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(item: CastModel) {
                binding.apply {
                    tvName.text = item.name

                    ivThumbnail.load("${Constant.BASE_URL_IMAGE_500}${item.profilePath}")
                    {
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
                        onClickCreditsCast(item)
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

    class Crew(private val onClickCreditsCrew: OnClickCreditsCrew) :

        ListAdapter<CrewModel, Crew.ViewHolder>(CrewCallback()) {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)
            item?.let { holder.bind(it) }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                SeeAllCreditsItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), onClickCreditsCrew
            )
        }

        inner class ViewHolder(
            private val binding: SeeAllCreditsItemBinding,
            private val onClickCreditsCrew: OnClickCreditsCrew
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

                    root.setOnClickListener {
                        onClickCreditsCrew(item)
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