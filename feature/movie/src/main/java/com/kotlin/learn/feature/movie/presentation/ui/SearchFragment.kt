package com.kotlin.learn.feature.movie.presentation.ui

import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.nav.navigator.MovieNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.ONE
import com.kotlin.learn.core.utilities.Constant.THREE
import com.kotlin.learn.core.utilities.Constant.TWO
import com.kotlin.learn.core.utilities.Constant.ZERO
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.core.utilities.show
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.adapter.SearchAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentSearchBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.SearchViewModel
import com.kotlin.learn.feature.movie.util.common.SearchLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val searchAdapter = SearchAdapter(this::onMovieClicked)

    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun setupView() {
        subscribeSearch()
        setupEditTextChanged()
        setupAdapter()
        getInitialMovie()
    }

    private fun getInitialMovie() {
        binding.viewAnimator.show()
        viewModel.searchMovie(Constant.EMPTY_STRING)
    }

    private fun subscribeSearch() = with(viewModel) {
        searchMovie.launch(this@SearchFragment) {
            searchAdapter.submitData(it)
        }
    }

    private fun setupEditTextChanged() = with(binding) {
        etSearch.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrEmpty()) {
                viewAnimator.show()
                viewModel.searchMovie(text.toString())
            }
        }
    }

    private fun setupAdapter() = with(binding) {
        rvSearch.apply {
            layoutManager = GridLayoutManager(activity, THREE)
            adapter = searchAdapter.withLoadStateHeaderAndFooter(
                SearchLoadStateAdapter { searchAdapter.retry() },
                SearchLoadStateAdapter { searchAdapter.retry() }
            )
        }

        searchAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    viewAnimator.displayedChild = ZERO
                }

                is LoadState.NotLoading -> {
                    if (searchAdapter.itemCount == ZERO) {
                        setViewBasedOnState(loadState, getString(R.string.empty_data_title))
                        viewAnimator.displayedChild = TWO
                    } else viewAnimator.displayedChild = ONE
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
                    setOnClickListener { searchAdapter.retry() }
                }
            }
            viewAnimator.displayedChild = TWO
        }
    }

    private fun onMovieClicked(item: MovieDataModel) {
        movieNavigator.fromSearchToDetail(this, item)
    }

}