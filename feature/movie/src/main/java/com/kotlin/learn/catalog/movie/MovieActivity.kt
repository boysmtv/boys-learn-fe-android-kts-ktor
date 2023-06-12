package com.kotlin.learn.catalog.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.ActivityMovieBinding
import com.kotlin.learn.catalog.movie.adapter.MovieAdapter
import com.kotlin.learn.catalog.movie.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieBinding

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = MovieAdapter()
        binding.recyclerview.adapter = adapter
    }

}