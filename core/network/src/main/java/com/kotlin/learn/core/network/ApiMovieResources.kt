package com.kotlin.learn.core.network

import io.ktor.resources.Resource

@Resource("movie/popular")
class PopularMovie

@Resource("movie/top_rated")
class TopRatedMovie

@Resource("movie/upcoming")
class UpComingMovie

@Resource("movie/now_playing")
class NowPlayingMovie

@Resource("movie")
class DetailMovie

@Resource("search/movie")
class SearchMovie

@Resource("movie")
class CreditsMovie