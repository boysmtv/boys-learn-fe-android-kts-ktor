package com.kotlin.learn.catalog.movie.util.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.learn.catalog.core.utilities.hide
import com.kotlin.learn.catalog.feature.movie.databinding.ViewLoadStateBinding

class LoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<com.kotlin.learn.catalog.movie.util.common.MovieLoadStateAdapter.LoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            ViewLoadStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ViewHolder(
        private val binding: ViewLoadStateBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                lyShimmer.root.hide()
                viewCommonError.apply {
                    buttonRetry.setOnClickListener { retry.invoke() }
                }
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Error) {
                    viewCommonError.errorMessage.text = loadState.error.localizedMessage
                }

                when (loadState) {
                    is LoadState.NotLoading -> {}
                    LoadState.Loading -> root.displayedChild = 0
                    is LoadState.Error -> root.displayedChild = 1
                }
            }
        }
    }
}