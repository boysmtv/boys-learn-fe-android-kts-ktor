package com.kotlin.learn.catalog.movie.presentation.ui

import android.graphics.BitmapFactory
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
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.nav.navigator.MovieNavigator
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import com.kotlin.learn.catalog.core.utilities.extension.launch
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.FragmentHomeBinding
import com.kotlin.learn.catalog.feature.movie.databinding.MovieHomeBinding
import com.kotlin.learn.catalog.movie.adapter.CommonLoadStateAdapter
import com.kotlin.learn.catalog.movie.adapter.MovieAdapter
import com.kotlin.learn.catalog.movie.adapter.MovieBannerAdapter
import com.kotlin.learn.catalog.movie.presentation.viewmodel.HomeViewModel
import com.zhpan.bannerview.constants.PageStyle
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.blurry.Blurry
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val adapterPopular = MovieAdapter(this::onMovieClicked, MovieCategories.POPULAR)
    private val adapterTopRated = MovieAdapter(this::onMovieClicked, MovieCategories.TOP_RATED)
    private val adapterUpComing = MovieAdapter(this::onMovieClicked, MovieCategories.UP_COMING)

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
        subscribeMovie()
        setupView()
        setupViewPager()
        subscribeBanner()
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
        setupAdapterMovie(layoutPopular, adapterPopular)

        layoutTopRated.tvMoviePopularTitle.text = getString(R.string.top_rated_title)
        setupAdapterMovie(layoutTopRated, adapterTopRated)

        layoutMustWatch.tvMoviePopularTitle.text = getString(R.string.up_coming_title)
        setupAdapterMovie(layoutMustWatch, adapterUpComing)

        Blurry.with(context)
            .radius(10)
            .sampling(16)
            .from(BitmapFactory.decodeResource(resources, R.drawable.background_app_3))
            .into(binding.layoutBackground.ivMovieBackground)
    }

    private fun setupAdapterMovie(layoutPopular: MovieHomeBinding, movieAdapter: MovieAdapter) =
        with(layoutPopular) {
            recyclerview.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
                            setViewBasedOnState(layoutPopular, loadState, getString(R.string.empty_data_title))
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

    private fun setViewBasedOnState(
        binding: MovieHomeBinding,
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
        movieNavigator.navigateToDetailMovie(this)
    }

    private fun setupViewPager() = binding.layoutBanner.bannerVpHome
        .setPageStyle(PageStyle.MULTI_PAGE_SCALE, 0.9f)
        .setRevealWidth(
            resources.getDimensionPixelOffset(R.dimen.dp_16),
            resources.getDimensionPixelOffset(R.dimen.dp_16)
        )
        .setPageMargin(resources.getDimensionPixelOffset(R.dimen.dp_12))
        .registerLifecycleObserver(lifecycle)
        .setAdapter(MovieBannerAdapter())
        .create()

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