package com.kotlin.learn.catalog.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.kotlin.learn.catalog.core.model.NetworkMovieData
import com.kotlin.learn.catalog.feature.movie.databinding.ActivityMovieBinding
import com.kotlin.learn.catalog.feature.movie.databinding.MovieItemLayoutBinding
import com.kotlin.learn.catalog.movie.adapter.CommonLoadStateAdapter
import com.kotlin.learn.catalog.movie.adapter.MovieAdapter
import com.kotlin.learn.catalog.movie.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    private val adapterPopular = MovieAdapter(this::onMovieClicked)
    private val adapterTopRated = MovieAdapter(this::onMovieClicked)
    private val adapterMustWatched = MovieAdapter(this::onMovieClicked)

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeMovie()
        setupAdapter()
    }

    private fun subscribeMovie() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.popularMovies.collectLatest {
                    adapterPopular.submitData(it)
                }
                viewModel.topRatedMovies.collectLatest {
                    adapterTopRated.submitData(it)
                }
                viewModel.mustWatchedMovies.collectLatest {
                    adapterMustWatched.submitData(it)
                }
            }
        }
    }

    private fun setupAdapter() = with(binding) {
        setupPopularMovie(layoutPopular)
        setupTopRatedMovie(layoutTopRated)
        setupMustWatchedMovie(layoutMustWatch)
    }

    private fun setupPopularMovie(layoutPopular: MovieItemLayoutBinding) = with(layoutPopular) {
        recyclerview.adapter = adapterPopular.withLoadStateHeaderAndFooter(
            CommonLoadStateAdapter { adapterPopular.retry() },
            CommonLoadStateAdapter { adapterPopular.retry() }
        )

        adapterPopular.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    viewAnimator.displayedChild = 0
                }

                is LoadState.NotLoading -> {
                    if (adapterPopular.itemCount == 0) {
                        setViewBasedOnState(loadState, "Kosong")
                        viewAnimator.displayedChild = 2
                    } else {
                        viewAnimator.displayedChild = 1
                    }
                }

                is LoadState.Error -> {
                    val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                    setViewBasedOnState(loadState, errorMessage.toString())
                }
            }
        }
    }

    private fun setupTopRatedMovie(layoutTopRated: MovieItemLayoutBinding) = with(layoutTopRated) {
        recyclerview.adapter = adapterTopRated.withLoadStateHeaderAndFooter(
            CommonLoadStateAdapter { adapterTopRated.retry() },
            CommonLoadStateAdapter { adapterTopRated.retry() }
        )

        adapterTopRated.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    viewAnimator.displayedChild = 0
                }

                is LoadState.NotLoading -> {
                    if (adapterTopRated.itemCount == 0) {
                        setViewBasedOnState(loadState, "Kosong")
                        viewAnimator.displayedChild = 2
                    } else {
                        viewAnimator.displayedChild = 1
                    }
                }

                is LoadState.Error -> {
                    val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                    setViewBasedOnState(loadState, errorMessage.toString())
                }
            }
        }
    }

    private fun setupMustWatchedMovie(layoutMustWatch: MovieItemLayoutBinding) =
        with(layoutMustWatch) {
            recyclerview.adapter = adapterMustWatched.withLoadStateHeaderAndFooter(
                CommonLoadStateAdapter { adapterMustWatched.retry() },
                CommonLoadStateAdapter { adapterMustWatched.retry() }
            )

            adapterMustWatched.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.Loading -> {
                        viewAnimator.displayedChild = 0
                    }

                    is LoadState.NotLoading -> {
                        if (adapterMustWatched.itemCount == 0) {
                            setViewBasedOnState(loadState, "Kosong")
                            viewAnimator.displayedChild = 2
                        } else {
                            viewAnimator.displayedChild = 1
                        }
                    }

                    is LoadState.Error -> {
                        val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                        setViewBasedOnState(
                            binding.layoutPopular.viewCommonError
                            loadState, errorMessage.toString())
                    }
                }
            }
        }

    private fun setViewBasedOnState(
        loadState: CombinedLoadStates,
        errorView : Binding,
        message: String
    ) {
       errorView.apply {
            viewCommonError.apply {
                errorMessage.text = message
                buttonRetry.apply {
                    isVisible = loadState.source.refresh is LoadState.Error
                    setOnClickListener { adapterPopular.retry() }
                }
            }
            viewAnimator.displayedChild = 2
        }
    }

    private fun onMovieClicked(item: NetworkMovieData) {

    }

}