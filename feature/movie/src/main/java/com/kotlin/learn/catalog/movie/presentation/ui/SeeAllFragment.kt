package com.kotlin.learn.catalog.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.nav.navigator.MovieNavigator
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.core.utilities.MovieCategories
import com.kotlin.learn.catalog.core.utilities.extension.capitalize
import com.kotlin.learn.catalog.core.utilities.extension.launch
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.FragmentSeeAllBinding
import com.kotlin.learn.catalog.movie.util.common.MovieLoadStateAdapter
import com.kotlin.learn.catalog.movie.adapter.SeeAllAdapter
import com.kotlin.learn.catalog.movie.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeeAllFragment : Fragment() {

    private val seeAllAdapter = SeeAllAdapter(this::onMovieClicked)
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentSeeAllBinding
    private val args: SeeAllFragmentArgs by navArgs()

    private var categories = Constant.EMPTY_STRING

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadArguments()
        subscribeMovie()
        setupView()
        setupAdapterMovie()
    }

    private fun loadArguments() {
        categories = args.categories
    }

    private fun subscribeMovie() = with(viewModel) {
        with(this@SeeAllFragment) {
            when (categories) {
                MovieCategories.POPULAR.name -> {
                    popularMovies.launch(this) { seeAllAdapter.submitData(it) }
                }

                MovieCategories.TOP_RATED.name -> {
                    topRatedMovies.launch(this) { seeAllAdapter.submitData(it) }
                }

                MovieCategories.UP_COMING.name -> {
                    upComingMovies.launch(this) { seeAllAdapter.submitData(it) }
                }
            }
        }
    }

    private fun setupView() = with(binding) {
        tvSeeAllTitle.text = categories.replace("_", " ").capitalize()
    }

    private fun setupAdapterMovie() =
        with(binding) {
            rvSeeAll.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
                adapter = seeAllAdapter.withLoadStateHeaderAndFooter(
                    MovieLoadStateAdapter { seeAllAdapter.retry() },
                    MovieLoadStateAdapter { seeAllAdapter.retry() }
                )
            }

            seeAllAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.Loading -> {
                        viewAnimator.displayedChild = 0
                    }

                    is LoadState.NotLoading -> {
                        if (seeAllAdapter.itemCount == 0) {
                            setViewBasedOnState(loadState, getString(R.string.empty_data_title))
                            viewAnimator.displayedChild = 2
                        } else viewAnimator.displayedChild = 1
                    }

                    is LoadState.Error -> {
                        val errorMessage = (loadState.source.refresh as LoadState.Error).error.message
                        setViewBasedOnState(loadState, errorMessage.toString())
                    }
                }
            }
        }

    private fun setViewBasedOnState(
        loadState: CombinedLoadStates,
        message: String
    ) {
        with(binding) {
            viewCommonError.apply {
                errorMessage.text = message
                buttonRetry.apply {
                    isVisible = loadState.source.refresh is LoadState.Error
                    setOnClickListener { seeAllAdapter.retry() }
                }
            }
            viewAnimator.displayedChild = 2
        }
    }

    private fun onMovieClicked(item: MovieDataModel) {
        movieNavigator.fromSeeAllToDetailMovie(this, item)
    }

}