package com.kotlin.learn.catalog.movie.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kotlin.learn.catalog.core.common.Result
import com.kotlin.learn.catalog.core.utilities.extension.launch
import com.kotlin.learn.catalog.feature.movie.databinding.FragmentDetailBinding
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

    }

    private fun subscribeDetail() = with(viewModel) {
        detailMovies.launch(this@DetailFragment) {
            when (it) {
                Result.Loading -> {
                    with(binding) {
                        tvDetailHeaderTitle.text = "Loading"
                    }
                }

                is Result.Success -> {
                    with(binding) {
                        tvDetailHeaderTitle.text = "Success"
                    }
                }

                is Result.Error -> {
                    with(binding) {
                        tvDetailHeaderTitle.text = "Error"
                    }
                }
            }
        }
    }

    private fun loadArguments() {
        setupView(args.movieId)
    }

    private fun setupView(movieId: String) {
        viewModel.getDetailMovies(movieId = movieId)
    }

    private fun changeDateFormat(strDate : String): String {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd")
        val newFormat = SimpleDateFormat("dd-MM-yyyy")
        return newFormat.format(defaultFormat.parse(strDate)!!)
    }

}