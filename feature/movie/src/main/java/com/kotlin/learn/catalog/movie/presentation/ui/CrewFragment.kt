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
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.FragmentCrewBinding
import com.kotlin.learn.catalog.movie.adapter.CrewAdapter
import com.kotlin.learn.catalog.movie.presentation.viewmodel.CrewViewModel
import com.kotlin.learn.catalog.movie.util.common.SearchLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrewFragment : Fragment() {

    private val crewAdapter = CrewAdapter()

    private lateinit var binding: FragmentCrewBinding

    private val viewModel : CrewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeCrew()
        setupView()
    }

    private fun subscribeCrew() = with(viewModel){

    }

    private fun setupView(){
        setupAdapter()
    }

    private fun setupAdapter() = with(binding) {
        rvCrew.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = crewAdapter.withLoadStateHeaderAndFooter(
                SearchLoadStateAdapter { crewAdapter.retry() },
                SearchLoadStateAdapter { crewAdapter.retry() }
            )
        }

        crewAdapter.addLoadStateListener { loadState ->
            when (loadState.source.refresh) {
                is LoadState.Loading -> {
                    viewAnimator.displayedChild = 0
                }

                is LoadState.NotLoading -> {
                    if (crewAdapter.itemCount == 0) {
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
                    setOnClickListener { crewAdapter.retry() }
                }
            }
            viewAnimator.displayedChild = 2
        }
    }
}