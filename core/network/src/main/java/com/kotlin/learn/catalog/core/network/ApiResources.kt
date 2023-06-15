package com.kotlin.learn.catalog.core.network

import io.ktor.resources.Resource

@Resource("discover/movie")
class DiscoverMovie

@Resource("movie/popular")
class PopularMovie
@Resource("movie/top_rated")
class TopRatedMovie
@Resource("movie/upcoming")
class UpComingMovie
