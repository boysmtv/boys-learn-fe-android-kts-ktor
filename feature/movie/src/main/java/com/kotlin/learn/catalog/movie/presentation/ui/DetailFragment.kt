package com.kotlin.learn.catalog.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.model.Genres
import com.kotlin.learn.catalog.core.model.MovieDetailModel
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.core.utilities.extension.launch
import com.kotlin.learn.catalog.feature.movie.databinding.FragmentDetailBinding
import com.kotlin.learn.catalog.movie.adapter.DetailCastAdapter
import com.kotlin.learn.catalog.movie.presentation.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeDetail()
        loadArguments()
        initialAdapter()
    }

    private fun subscribeDetail() = with(binding) {
        viewModel.detailMovies.launch(this@DetailFragment) {
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

    private fun initialAdapter() = with(binding) {
        val castAdapter = DetailCastAdapter(activity)
        castAdapter.addFragment(CrewFragment(), "Cast")
        castAdapter.addFragment(CrewFragment(), "Director & Crew")

        tlDetailCast.apply {
            tabGravity = TabLayout.GRAVITY_FILL
        }
        vpDetailCast.apply {
            adapter = castAdapter
            currentItem = 0
        }
        TabLayoutMediator(tlDetailCast, vpDetailCast) { tab, position ->
            tab.text = castAdapter.getTabTitle(position)
        }.attach()
    }

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
        movie.belongsToCollection?.backdropPath?.let {
            Glide.with(this@DetailFragment)
                .load("${Constant.BASE_URL_IMAGE}${it}")
                .into(ivDetailPlay)
        }
    }

}