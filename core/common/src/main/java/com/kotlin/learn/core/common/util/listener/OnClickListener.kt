package com.kotlin.learn.core.common.util.listener

import androidx.viewbinding.ViewBinding
import com.kotlin.learn.core.model.CastModel
import com.kotlin.learn.core.model.CrewModel
import com.kotlin.learn.core.model.FavouriteDataModel
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.RecentDataModel

typealias OnClickMovie = (MovieDataModel) -> Unit

typealias OnClickCreditsCast = (CastModel) -> Unit

typealias OnClickCreditsCrew = (CrewModel) -> Unit

typealias OnClickFavourite = (FavouriteDataModel) -> Unit

typealias OnClickRecent = (RecentDataModel) -> Unit