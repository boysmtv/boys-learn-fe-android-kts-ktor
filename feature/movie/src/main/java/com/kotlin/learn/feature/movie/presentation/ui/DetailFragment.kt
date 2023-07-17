package com.kotlin.learn.feature.movie.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.learn.core.common.Result
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.model.Genres
import com.kotlin.learn.core.model.MovieDetailModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.adapter.DetailCreditsAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentDetailBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeDetail()
        loadArguments()
        setupVpCredits()
    }

    override fun setupView() {

    }

    private fun subscribeDetail() = with(binding) {
        viewModel.detailMovies.launch(this@DetailFragment) {
            when (it) {
                Result.Loading -> {
                    viewAnimator.displayedChild = Constant.ZERO
                }

                is Result.Success -> {
                    viewAnimator.displayedChild = Constant.ONE
                    loadContent(it.data)
                }

                is Result.Error -> {
                    viewAnimator.displayedChild = Constant.TWO
                }
            }
        }
    }

    private fun loadContent(it: MovieDetailModel) = with(binding) {
        tvDetailHeaderTitle.text = it.title
        tvDetailHeaderRuleGenres.text = convertGenres(it.genres)
        tvDetailHeaderRuleTime.text = calculateRuntime(it.runtime)
        tvDetailHeaderRuleYear.text = convertDatetimeToDate(it.releaseDate)
        tvDetailHeaderDesc.text = it.overview
        setupThumbnail(it)
    }

    private fun setupVpCredits() = with(binding) {
        val creditsAdapter = DetailCreditsAdapter(activity)
        creditsAdapter.addFragment(
            fragment = CreditsFragment(
                isCrew = Constant.FALSE,
                movieId = args.movieId
            ),
            title = "Cast"
        )
        creditsAdapter.addFragment(
            fragment = CreditsFragment(
                isCrew = Constant.TRUE,
                movieId = args.movieId
            ),
            title = "Director & Crew"
        )

        tlDetailCredits.apply {
            tabGravity = TabLayout.GRAVITY_FILL
        }
        vpDetailCredits.apply {
            adapter = creditsAdapter
            currentItem = Constant.ZERO
            isUserInputEnabled = Constant.FALSE
        }
        TabLayoutMediator(tlDetailCredits, vpDetailCredits) { tab, position ->
            tab.text = creditsAdapter.getTabTitle(position)
        }.attach()
    }

    @SuppressLint("SimpleDateFormat")
    private fun convertDatetimeToDate(releaseDate: String?): String {
        releaseDate?.let {
            val currFormatter = SimpleDateFormat("yyyy")
            return currFormatter.format(currFormatter.parse(releaseDate)!!)
        }
        return Constant.EMPTY_STRING
    }

    private fun loadArguments() {
        setupView(args.movieId)
    }

    private fun setupView(movieId: String) {
        viewModel.getDetailMovies(movieId = movieId)
    }

    private fun calculateRuntime(timeRuntime: Int?): String {
        timeRuntime?.let {
            var startTime = timeRuntime
            var startHour = Constant.ZERO
            for (i in 0..startTime) {
                if (startTime < 60) {
                    break
                } else {
                    startTime -= 60
                    startHour++
                    continue
                }
            }
            return "${startHour}h ${timeRuntime % 60}m"
        }
        return Constant.EMPTY_STRING
    }

    private fun convertGenres(genres: ArrayList<Genres>): String {
        return genres[0].name.toString()
    }

    private fun setupThumbnail(movie: MovieDetailModel) = with(binding) {
        movie.backdropPath?.let {
            Glide.with(this@DetailFragment)
                .load("${Constant.BASE_URL_IMAGE}${it}")
                .into(ivDetailPlay)
        }
    }

}