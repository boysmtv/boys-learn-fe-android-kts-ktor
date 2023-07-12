package com.kotlin.learn.catalog.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.common.isError
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.model.MovieModel
import com.kotlin.learn.catalog.core.nav.navigator.MovieNavigator
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import com.kotlin.learn.catalog.core.utilities.extension.launch
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.FragmentHomeBinding
import com.kotlin.learn.catalog.feature.movie.databinding.MovieHomeBinding
import com.kotlin.learn.catalog.movie.adapter.MovieAdapter
import com.kotlin.learn.catalog.movie.adapter.MovieBannerAdapter
import com.kotlin.learn.catalog.movie.presentation.viewmodel.HomeViewModel
import com.kotlin.learn.catalog.movie.util.common.MovieLoadStateAdapter
import com.zhpan.bannerview.constants.PageStyle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val adapterPopular = MovieAdapter(this::onMovieClicked)
    private val adapterTopRated = MovieAdapter(this::onMovieClicked)
    private val adapterUpComing = MovieAdapter(this::onMovieClicked)

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSwipeRefresh()
        subscribeMovie()
        setupView()
        setupViewPager()
        subscribeBanner()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.nowPlayingMovies()
            binding.swipeRefresh.isRefreshing = false
        }

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

    private fun setupView() = with(binding) {
        layoutPopular.tvMoviePopularTitle.text = getString(R.string.popular_title)
        setupAdapterMovie(layoutPopular, adapterPopular, MovieCategories.POPULAR)

        layoutTopRated.tvMoviePopularTitle.text = getString(R.string.top_rated_title)
        setupAdapterMovie(layoutTopRated, adapterTopRated, MovieCategories.TOP_RATED)

        layoutUpComing.tvMoviePopularTitle.text = getString(R.string.up_coming_title)
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