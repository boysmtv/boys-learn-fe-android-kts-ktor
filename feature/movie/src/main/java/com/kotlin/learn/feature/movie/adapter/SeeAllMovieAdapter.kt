package com.kotlin.learn.feature.movie.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.listener.OnClickMovie
import com.kotlin.learn.core.common.data.preferences.DataStorePreferences
import com.kotlin.learn.core.data.callback.MovieCallback
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.FIVE_FLOAT
import com.kotlin.learn.core.utilities.Constant.THIRTY_FLOAT
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.core.utilities.extension.convertDateFormat
import com.kotlin.learn.core.utilities.extension.getMonthName
import com.kotlin.learn.core.utilities.extension.runSafeLaunch
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieSeeAllItemBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat


class SeeAllMovieAdapter(
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil,
    private val onClickMovie: OnClickMovie,
    private val onClickFavourite: OnClickMovie
) :
    PagingDataAdapter<MovieDataModel, SeeAllMovieAdapter.ViewHolder>(MovieCallback()) {

    private var userModel = UserModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        fetchUserModelFromDataStore()
        return ViewHolder(
            MovieSeeAllItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClickMovie
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    private fun fetchUserModelFromDataStore() {
        runSafeLaunch {
            val data = dataStore.getString(PreferenceConstants.Authorization.PREF_USER).getOrNull().orEmpty()
            if (data.isNotEmpty() && data.isNotBlank()) {
                try {
                    val model = jsonUtil.fromJson<UserModel>(data)
                    model?.let {
                        userModel = it
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun checkIsFavouriteSaved(binding: MovieSeeAllItemBinding, item: MovieDataModel) {
        val isFavourite = userModel.favourite.any { it.id == item.id }
        setChangeUiFavourite(binding, isFavourite)
    }

    private fun setChangeUiFavourite(binding: MovieSeeAllItemBinding, isSaved: Boolean) = with(binding) {
        if (isSaved) {
            ivSeeAllContentIconFavourite.setImageResource(R.drawable.ic_saved)
            tvSeeAllContentIconFavourite.setText(R.string.saved)
        } else {
            ivSeeAllContentIconFavourite.setImageResource(R.drawable.ic_favourite)
            tvSeeAllContentIconFavourite.setText(R.string.favourite)
        }
    }

    inner class ViewHolder(
        private val binding: MovieSeeAllItemBinding,
        private val onClickMovie: OnClickMovie,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(item: MovieDataModel) {

            checkIsFavouriteSaved(binding, item)

            binding.apply {
                tvSeeAllContentTitle.text = item.originalTitle
                tvSeeAllContentDesc.text = item.overview
                tvSeeAllContentDate.text = "Show time ${item.releaseDate?.convertDateFormat()}"

                ivSeeAllContentMovie.load("${Constant.BASE_URL_IMAGE_500}${item.backdropPath}") {
                    val context = root.context
                    val circularProgressDrawable = CircularProgressDrawable(context).apply {
                        strokeWidth = FIVE_FLOAT
                        centerRadius = THIRTY_FLOAT
                        strokeCap = Paint.Cap.BUTT
                        start()
                    }
                    placeholder(circularProgressDrawable)
                    error(R.drawable.ic_baseline_broken_image_24)
                }

                item.releaseDate?.let { releaseDate ->
                    tvSeeAllDate.text = SimpleDateFormat("dd").parse(releaseDate)
                        ?.let { date -> SimpleDateFormat("dd").format(date) }

                    tvSeeAllMonth.text = SimpleDateFormat("MM").parse(releaseDate)
                        ?.let { month -> SimpleDateFormat("MM").format(month) }?.toInt()?.getMonthName()
                }

                clSeeAllFavourite.setOnClickListener {
                    onClickFavourite(item)
                }

                root.setOnClickListener {
                    onClickMovie(item)
                }
            }
        }
    }

}
