package com.kotlin.learn.feature.movie.presentation.ui

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.MovieCategories
import com.kotlin.learn.core.utilities.extension.capitalize
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.common.viewmodel.UserViewModel
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.adapter.SeeAllMovieAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentSeeAllBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.HomeViewModel
import com.kotlin.learn.feature.movie.util.common.MovieLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SeeAllMovieFragment : BaseFragment<FragmentSeeAllBinding>(FragmentSeeAllBinding::inflate) {

    private lateinit var seeAllMovieAdapter: SeeAllMovieAdapter

    private val viewModel: HomeViewModel by viewModels()

    private val userViewModel: UserViewModel by viewModels()

    private var userModel: UserModel = UserModel()

    private val args: SeeAllMovieFragmentArgs by navArgs()

    private var categories = Constant.EMPTY_STRING

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun setupView() {
        loadArguments()
        loadUser()
        setupAdapter()
        subscribeMovie()
        setupAdapterMovie()
    }

    private fun loadArguments() {
        categories = args.categories
        binding.tvSeeAllTitle.text = categories.replace("_", " ").capitalize()
    }

    private fun loadUser() {
        userViewModel.fetchUserFromDatastore().launch(this@SeeAllMovieFragment) { event ->
            invokeDataStoreEvent(event,
                isFetched = {
                    userModel = it
                },
                isError = {
                    showDialogGeneralError("Warning", "Failed to fetch your profile")
                }
            )
        }
    }

    private fun setupAdapter() {
        seeAllMovieAdapter = SeeAllMovieAdapter(
            dataStore = dataStorePreferences,
            jsonUtil = jsonUtil,
            onClickMovie = this::onMovieClicked,
            onClickFavourite = this::onMovieFavourite
        )
    }

    private fun subscribeMovie() = with(viewModel) {
        with(this@SeeAllMovieFragment) {
            when (categories) {
                MovieCategories.POPULAR.name -> {
                    popularMovies.launch(this) { seeAllMovieAdapter.submitData(it) }
                }

                MovieCategories.TOP_RATED.name -> {
                    topRatedMovies.launch(this) { seeAllMovieAdapter.submitData(it) }
                }

                MovieCategories.UP_COMING.name -> {
                    upComingMovies.launch(this) { seeAllMovieAdapter.submitData(it) }
                }
            }
        }
    }

    private fun setupAdapterMovie() =
        with(binding) {
            rvSeeAll.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
                adapter = seeAllMovieAdapter.withLoadStateHeaderAndFooter(
                    MovieLoadStateAdapter { seeAllMovieAdapter.retry() },
                    MovieLoadStateAdapter { seeAllMovieAdapter.retry() }
                )
            }

            seeAllMovieAdapter.addLoadStateListener { loadState ->
                when (loadState.source.refresh) {
                    is LoadState.Loading -> {
                        viewAnimator.displayedChild = 0
                    }

                    is LoadState.NotLoading -> {
                        if (seeAllMovieAdapter.itemCount == 0) {
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
                    setOnClickListener { seeAllMovieAdapter.retry() }
                }
            }
            viewAnimator.displayedChild = 2
        }
    }

    private fun onMovieFavourite(item: MovieDataModel) {

    }

    private fun onMovieClicked(item: MovieDataModel) {
        movieNavigator.fromSeeAllMovieToDetailMovie(this, item)
    }

}