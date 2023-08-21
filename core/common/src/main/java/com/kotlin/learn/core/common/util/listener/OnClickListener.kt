package com.kotlin.learn.core.common.util.listener

import com.kotlin.learn.core.model.CastModel
import com.kotlin.learn.core.model.CrewModel
import com.kotlin.learn.core.model.MovieDataModel

typealias OnClickMovie = (MovieDataModel) -> Unit

typealias OnClickCreditsCast = (CastModel) -> Unit

typealias OnClickCreditsCrew = (CrewModel) -> Unit