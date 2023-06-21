package com.kotlin.learn.catalog.movie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import com.kotlin.learn.catalog.core.utilities.extension.launch
import com.kotlin.learn.catalog.feature.movie.databinding.ActivityMovieBinding
import com.kotlin.learn.catalog.feature.movie.databinding.MovieItemLayoutBinding
import com.kotlin.learn.catalog.movie.adapter.CommonLoadStateAdapter
import com.kotlin.learn.catalog.movie.adapter.MovieAdapter
import com.kotlin.learn.catalog.movie.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    private val adapterPopular = MovieAdapter(this::onMovieClicked, MovieCategories.POPULAR)
    private val adapterTopRated = MovieAdapter(this::onMovieClicked, MovieCategories.TOP_RATED)
    private val adapterUpComing = MovieAdapter(this::onMovieClicked, MovieCategories.UP_COMING)

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeMovie()
        setupView()
    }

    private fun subscribeMovie() {
//        viewModel.popularMovies.launch(lifecycleOwner = this) { adapterPopular.submitData(it) }
//        viewModel.topRatedMovies.launch(lifecycleOwner = this) { adapterTopRated.submitData(it) }
//        viewModel.upComingMovies.launch(lifecycleOwner = this) { adapterUpComing.submitData(it) }
    }

    private fun setupAdapterMovie(layoutPopular: MovieItemLayoutBinding, movieAdapter: MovieAdapter) =
        with(layoutPopular) {
            recyclerview.apply {
                layoutManager = LinearLayoutManager(this@MovieActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = movieAdapter.withLoadStateHeaderAndFooter(
                    CommonLoadStateAdapter { movieAdapter.retry() },
                    CommonLoadStateAdapter { movieAdapter.retry() }
                )
            }

            movieAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.Loading -> {
                        viewAnimator.displayedChild = 0
                    }

                    is LoadState.NotLoading -> {
                        if (movieAdapter.itemCount == 0) {
                            setViewBasedOnState(layoutPopular, loadState, "Kosong")
                            viewAnimator.displayedChild = 2
                        } else viewAnimator.displayedChild = 1
                    }

                    is LoadState.Error -> {
                        val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                        setViewBasedOnState(layoutPopular, loadState, errorMessage.toString())
                    }
                }
            }
        }

    private fun setupView() = with(binding) {
        layoutPopular.tvMoviePopularTitle.text = "Popular"
        setupAdapterMovie(layoutPopular, adapterPopular)

        layoutTopRated.tvMoviePopularTitle.text = "Top Rated"
        setupAdapterMovie(layoutTopRated, adapterTopRated)

        layoutMustWatch.tvMoviePopularTitle.text = "Up Coming"
        setupAdapterMovie(layoutMustWatch, adapterUpComing)
    }

    private fun setViewBasedOnState(
        binding: MovieItemLayoutBinding,
        loadState: CombinedLoadStates,
        message: String
    ) {
        with(binding) {
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

    private fun onMovieClicked(item: MovieDataModel, categories: MovieCategories) {

    }

}