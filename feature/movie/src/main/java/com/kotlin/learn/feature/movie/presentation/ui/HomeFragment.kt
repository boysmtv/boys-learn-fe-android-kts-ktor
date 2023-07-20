package com.kotlin.learn.feature.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.core.utilities.MovieCategories
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.adapter.MovieAdapter
import com.kotlin.learn.feature.movie.adapter.MovieBannerAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentHomeBinding
import com.kotlin.learn.feature.movie.databinding.MovieHomeBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.HomeViewModel
import com.kotlin.learn.feature.movie.util.common.MovieLoadStateAdapter
import com.zhpan.bannerview.constants.PageStyle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val adapterPopular = MovieAdapter(this::onMovieClicked)
    private val adapterTopRated = MovieAdapter(this::onMovieClicked)
    private val adapterUpComing = MovieAdapter(this::onMovieClicked)

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSwipeRefresh()
        subscribeMovie()
        setupAdapter()
        setupViewPager()
        subscribeBanner()
        subscribeAuthorization()
        callAuthorization()
    }

    private fun setupSwipeRefresh() = with(binding) {
        swipeRefresh.apply {
            setOnRefreshListener {
                viewModel.nowPlayingMovies()
                isRefreshing = false
            }
        }
        adapterPopular.retry()
        adapterTopRated.retry()
        adapterUpComing.retry()
    }

    private fun subscribeMovie() = with(viewModel) {
        with(this@HomeFragment) {
            popularMovies.launch(this) { adapterPopular.submitData(it) }
            topRatedMovies.launch(this) { adapterTopRated.submitData(it) }
            upComingMovies.launch(this) { adapterUpComing.submitData(it) }
        }
    }

    private fun subscribeBanner() = with(binding.layoutBanner) {
        viewModel.nowPlayingMovies.launch(this@HomeFragment) {
            when (it) {
                Result.Loading -> {
                    viewAnimator.displayedChild = 0
                }

                is Result.Success -> {
                    viewAnimator.displayedChild = 1
                    bannerVpHome.refreshData(it.data.results)
                }

                is Result.Error -> {
                    viewAnimator.displayedChild = 2
                }
            }
        }
    }

    override fun setupView() = with(binding) {
        layoutPopular.tvMoviePopularTitle.text = getString(R.string.popular_title)
        layoutTopRated.tvMoviePopularTitle.text = getString(R.string.top_rated_title)
        layoutUpComing.tvMoviePopularTitle.text = getString(R.string.up_coming_title)
    }

    private fun setupAdapter() = with(binding) {
        setupAdapterMovie(layoutPopular, adapterPopular, MovieCategories.POPULAR)

        setupAdapterMovie(layoutTopRated, adapterTopRated, MovieCategories.TOP_RATED)

        setupAdapterMovie(layoutUpComing, adapterUpComing, MovieCategories.UP_COMING)

        ivSearch.setOnClickListener {
            movieNavigator.fromHomeToSearch(this@HomeFragment)
        }
    }

    private fun setupAdapterMovie(
        layoutBinding: MovieHomeBinding,
        movieAdapter: MovieAdapter,
        categories: MovieCategories
    ) =
        with(layoutBinding) {
            recyclerview.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                )
                adapter = movieAdapter.withLoadStateHeaderAndFooter(
                    MovieLoadStateAdapter { movieAdapter.retry() },
                    MovieLoadStateAdapter { movieAdapter.retry() }
                )
            }

            movieAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.Loading -> {
                        viewAnimator.displayedChild = 0
                    }

                    is LoadState.NotLoading -> {
                        if (movieAdapter.itemCount == 0) {
                            setViewBasedOnState(
                                layoutBinding,
                                movieAdapter,
                                loadState,
                                getString(R.string.empty_data_title)
                            )
                            viewAnimator.displayedChild = 2
                        } else {
                            viewAnimator.displayedChild = 1
                            setOnClickMore(layoutBinding, categories)
                        }
                    }

                    is LoadState.Error -> {
                        val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                        setViewBasedOnState(layoutBinding, movieAdapter, loadState, errorMessage.toString())
                    }
                }
            }
        }

    private fun setViewBasedOnState(
        binding: MovieHomeBinding,
        movieAdapter: MovieAdapter,
        loadState: CombinedLoadStates,
        message: String
    ) {
        with(binding) {
            viewCommonError.apply {
                errorMessage.text = message
                buttonRetry.apply {
                    isVisible = loadState.source.refresh is LoadState.Error
                    setOnClickListener { movieAdapter.retry() }
                }
            }
            viewAnimator.displayedChild = 2
        }
    }

    private fun setupViewPager() = binding.layoutBanner.bannerVpHome
        .setPageStyle(PageStyle.MULTI_PAGE_SCALE, 0.9f)
        .setRevealWidth(
            resources.getDimensionPixelOffset(R.dimen.dp_16),
            resources.getDimensionPixelOffset(R.dimen.dp_16)
        )
        .setPageMargin(resources.getDimensionPixelOffset(R.dimen.dp_12))
        .registerLifecycleObserver(lifecycle)
        .setAdapter(MovieBannerAdapter(this::onMovieClicked))
        .create()

    private fun onMovieClicked(item: MovieDataModel) {
        movieNavigator.navigateToDetailMovie(this, item)
    }

    private fun setOnClickMore(layoutBinding: MovieHomeBinding, categories: MovieCategories) =
        with(layoutBinding) {
            tvMoviePopularMore.setOnClickListener {
                movieNavigator.navigateToSeeAllMovie(this@HomeFragment, categories)
            }
        }


    private fun subscribeAuthorization() = with(viewModel) {
        fetchAuthorization.launch(this@HomeFragment) {
            when (it) {
                Result.Loading -> {
                    Log.e("BOYS-Home", "Value Loading auth: ${it}")
                }

                is Result.Success -> {
                    Log.e("BOYS-Home", "Value Success auth: $it")
                }

                is Result.Error -> {
                    Log.e("BOYS-Home", "Value Error auth: $it")
                }
            }

        }

    }

    private fun callAuthorization() = with(viewModel) {
        fetchDataFirebase("-N_lrbzAApAGJY7x_puw", AuthGoogleSignInModel())

    }

    override fun onPause() {
        binding.layoutBanner.bannerVpHome.stopLoop()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.layoutBanner.bannerVpHome.stopLoop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.layoutBanner.bannerVpHome.stopLoop()
    }

}