package com.kotlin.learn.feature.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.databinding.FragmentCreditsBinding
import com.kotlin.learn.feature.movie.adapter.CreditsAdapter
import com.kotlin.learn.feature.movie.presentation.viewmodel.CreditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditsFragment(
    private val isCrew: Boolean = Constant.FALSE,
    private val movieId: String
) : Fragment() {

    private val castAdapter = CreditsAdapter.Cast()
    private val crewAdapter = CreditsAdapter.Crew()

    private lateinit var binding: FragmentCreditsBinding

    private val viewModel: CreditsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeCredits()
        setupView()
        setupInit()
    }

    private fun subscribeCredits() = with(binding) {
        viewModel.creditsMovie.launch(this@CreditsFragment) {
            when (it) {
                Result.Loading -> {
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

    private fun setupView() {
        setupAdapter()
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