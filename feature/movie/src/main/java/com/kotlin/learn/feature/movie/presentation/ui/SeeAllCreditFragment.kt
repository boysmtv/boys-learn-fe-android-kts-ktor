package com.kotlin.learn.feature.movie.presentation.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.model.CastModel
import com.kotlin.learn.core.model.CreditsModel
import com.kotlin.learn.core.model.CrewModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.adapter.DetailCreditsAdapter
import com.kotlin.learn.feature.movie.adapter.SeeAllCreditsAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentSeeAllCreditBinding
import com.kotlin.learn.feature.movie.databinding.SeeAllCreditsListBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.CreditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllCreditFragment : BaseFragment<FragmentSeeAllCreditBinding>(FragmentSeeAllCreditBinding::inflate) {

    private val args: SeeAllCreditFragmentArgs by navArgs()

    private var movieId = Constant.EMPTY_STRING

    override fun setupView() {
        loadArguments()
        setupVpCredits()
    }

    private fun loadArguments() {
        movieId = args.movieId
    }

    private fun setupVpCredits() = with(binding) {
        val creditsAdapter = DetailCreditsAdapter(activity)
        creditsAdapter.addFragment(
            fragment = SeeAllCreditsFragmentView(
                movieId = movieId,
                creditsCategory = CreditsCategory.CAST,
            ),
            title = "Cast"
        )
        creditsAdapter.addFragment(
            fragment = SeeAllCreditsFragmentView(
                movieId = movieId,
                creditsCategory = CreditsCategory.CREW,
            ),
            title = "Director & Crew"
        )

        tlSeeAllCredits.apply {
            tabGravity = TabLayout.GRAVITY_CENTER
        }
        vpSeeAllCredits.apply {
            adapter = creditsAdapter
            currentItem = Constant.ZERO
            isUserInputEnabled = Constant.FALSE
        }
        TabLayoutMediator(tlSeeAllCredits, vpSeeAllCredits) { tab, position ->
            tab.text = creditsAdapter.getTabTitle(position)
        }.attach()
    }

}

@AndroidEntryPoint
class SeeAllCreditsFragmentView(
    private val movieId: String,
    private val creditsCategory: CreditsCategory,
) : BaseFragment<SeeAllCreditsListBinding>(SeeAllCreditsListBinding::inflate) {

    private val castAdapter = SeeAllCreditsAdapter.Cast(this::onClickCreditsCast)
    private val crewAdapter = SeeAllCreditsAdapter.Crew(this::onClickCreditsCrew)

    private val viewModel: CreditsViewModel by viewModels()

    override fun setupView() {
        subscribeCredits()
        setupAdapter()
        setupInit()
    }

    private fun subscribeCredits() = with(binding) {
        viewModel.creditsMovie.launch(this@SeeAllCreditsFragmentView) {
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
        if (checkCreditsIsCrew(creditsCategory))
            crewAdapter.submitList(it.crew)
        else
            castAdapter.submitList(it.cast)
    }

    private fun setupInit() {
        viewModel.getCredits(movieId)
    }

    private fun setupAdapter() = with(binding) {
        rvSeeAllCredits.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            adapter = if (checkCreditsIsCrew(creditsCategory)) crewAdapter else castAdapter
        }
    }

    private fun showErrorView(message: String) {
        binding.viewCommonError.apply {
            errorMessage.text = message
            buttonRetry.setOnClickListener { setupInit() }
        }
    }

    private fun checkCreditsIsCrew(creditsCategory: CreditsCategory): Boolean {
        return creditsCategory.name == CreditsCategory.CREW.name
    }

    private fun onClickCreditsCrew(item: CrewModel) {

    }

    private fun onClickCreditsCast(item: CastModel) {

    }

}