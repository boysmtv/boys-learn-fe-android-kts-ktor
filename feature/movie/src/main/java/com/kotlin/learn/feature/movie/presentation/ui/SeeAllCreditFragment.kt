package com.kotlin.learn.feature.movie.presentation.ui

import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.EMPTY_STRING
import com.kotlin.learn.feature.movie.adapter.DetailCreditsAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentSeeAllCreditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllCreditFragment : BaseFragment<FragmentSeeAllCreditBinding>(FragmentSeeAllCreditBinding::inflate) {

    private val args: SeeAllCreditFragmentArgs by navArgs()

    private var movieId = EMPTY_STRING

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
            fragment = SeeAllCreditsViewFragment(
                movieId = movieId,
                creditsCategory = CreditsCategory.CAST,
            ),
            title = "Cast"
        )
        creditsAdapter.addFragment(
            fragment = SeeAllCreditsViewFragment(
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
