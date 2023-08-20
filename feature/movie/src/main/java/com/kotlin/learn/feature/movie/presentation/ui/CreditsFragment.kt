package com.kotlin.learn.feature.movie.presentation.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.adapter.CreditsAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentCreditsBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.CreditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditsFragment(
    private val isCrew: Boolean = Constant.FALSE,
    private val movieId: String
) : BaseFragment<FragmentCreditsBinding>(FragmentCreditsBinding::inflate) {

    private val castAdapter = CreditsAdapter.Cast()
    private val crewAdapter = CreditsAdapter.Crew()

    private val viewModel: CreditsViewModel by viewModels()

    override fun setupView() {
        subscribeCredits()
        setupAdapter()
        setupInit()
    }

    private fun subscribeCredits() = with(binding) {
        viewModel.creditsMovie.launch(this@CreditsFragment) {
            when (it) {
                is Result.Waiting -> {}

                is Result.Loading -> {
                    viewAnimator.displayedChild = 0
                }

                is Result.Success -> {
                    viewAnimator.displayedChild = 1
                    loadContent(it.data)
                }

                is Result.Error -> {
                    viewAnimator.displayedChild = 2
                    showErrorView(
                        it.throwable.message
                            ?: "Error occurred when fetching data from server. Please try again"
                    )
                }
            }
        }
    }

    private fun loadContent(it: CreditsModel) {
        if (isCrew) crewAdapter.submitList(it.crew)
        else castAdapter.submitList(it.cast)
    }

    private fun setupInit() {
        viewModel.getCredits(movieId)
    }

    private fun setupAdapter() = with(binding) {
        rvCrew.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            adapter = if (isCrew) crewAdapter else castAdapter
        }
    }

    private fun showErrorView(message: String) {
        binding.viewCommonError.apply {
            errorMessage.text = message
            buttonRetry.setOnClickListener { setupInit() }
        }
    }
}