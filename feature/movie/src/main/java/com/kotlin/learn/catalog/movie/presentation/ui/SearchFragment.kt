package com.kotlin.learn.catalog.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.model.MovieSearchModel
import com.kotlin.learn.catalog.core.nav.navigator.MovieNavigator
import com.kotlin.learn.catalog.core.utilities.extension.launch
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.FragmentSearchBinding
import com.kotlin.learn.catalog.movie.adapter.CommonLoadStateAdapter
import com.kotlin.learn.catalog.movie.adapter.SearchAdapter
import com.kotlin.learn.catalog.movie.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchAdapter = SearchAdapter(this::onMovieClicked)

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var movieNavigator: MovieNavigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeSearch()
        setupView()
    }

    private fun subscribeSearch() = with(viewModel) {
        searchMovie.launch(this@SearchFragment) {
            binding.viewAnimator.visibility = View.VISIBLE
            searchAdapter.submitData(it)
        }
    }

    private fun setupView() = with(binding) {
        setupAdapter()
        setupEditTextChanged()
        viewAnimator.visibility = View.INVISIBLE
    }

    private fun setupEditTextChanged() = with(binding) {
        btnSearch.setOnClickListener {
            viewModel.searchMovie(etSearch.text.toString())
        }
    }

    private fun setupAdapter() = with(binding) {
        rvSearch.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = searchAdapter.withLoadStateHeaderAndFooter(
                CommonLoadStateAdapter { searchAdapter.retry() },
                CommonLoadStateAdapter { searchAdapter.retry() }
            )
        }

        searchAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    viewAnimator.displayedChild = 0
                }

                is LoadState.NotLoading -> {
                    if (searchAdapter.itemCount == 0) {
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
                    //isVisible = loadState.source.refresh is LoadState.Error
                    setOnClickListener { searchAdapter.retry() }
                }
            }
            viewAnimator.displayedChild = 2
        }
    }

    private fun onMovieClicked(item: MovieDataModel) {
        movieNavigator.navigateToDetailMovie(this, item)
    }
}