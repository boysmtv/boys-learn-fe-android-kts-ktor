package com.kotlin.learn.feature.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.util.ImageUtil
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.listener.OnClickMovie
import com.kotlin.learn.core.common.util.security.DataStorePreferences
import com.kotlin.learn.core.data.callback.MovieCallback
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.core.utilities.extension.convertDateFormat
import com.kotlin.learn.core.utilities.extension.getMonthName
import com.kotlin.learn.core.utilities.extension.runSafeLaunch
import com.kotlin.learn.core.utilities.setTextAnimation
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.MovieSeeAllItemBinding
import java.text.SimpleDateFormat

class SeeAllMovieAdapter(
    private val context: Context,
    private val dataStore: DataStorePreferences,
    private val jsonUtil: JsonUtil,
    private val onClickMovie: OnClickMovie,
    private val onClickFavourite: OnClickMovie
) :
    PagingDataAdapter<MovieDataModel, SeeAllMovieAdapter.ViewHolder>(MovieCallback()) {

    private lateinit var binding: MovieSeeAllItemBinding

    private var userModel = UserModel()

    private var isSaved: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MovieSeeAllItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        fetchUserModelFromDataStore()
        return ViewHolder(binding, onClickMovie)
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

    fun checkIsFavouriteSaved(item: MovieDataModel, binding: MovieSeeAllItemBinding) {
        userModel.favourite.let {
            if (it.isNotEmpty()) {
                for (data in it) {
                    if (data.id == item.id) {
                        isSaved = true
                        Log.e("checkIsFavouriteSaved", "data: ${data.id}, item: ${item.id}")
                        setChangeUiFavourite(binding)
                        break
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setChangeUiFavourite(binding: MovieSeeAllItemBinding) = with(binding) {
        if (isSaved) {
            ImageUtil.imageViewAnimatedChange(
                context,
                ivSeeAllContentIconFavourite,
                BitmapFactory.decodeResource(context.resources, R.drawable.ic_saved)
            )
            tvSeeAllContentIconFavourite.setTextAnimation(context.getString(R.string.saved))
        } else {
            ImageUtil.imageViewAnimatedChange(
                context,
                ivSeeAllContentIconFavourite,
                BitmapFactory.decodeResource(context.resources, R.drawable.ic_favourite)
            )
            tvSeeAllContentIconFavourite.setTextAnimation(context.getString(R.string.favourite))
        }
    }

    inner class ViewHolder(
        private val binding: MovieSeeAllItemBinding,
        private val onClickMovie: OnClickMovie,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(item: MovieDataModel) {

            binding.apply {
                tvSeeAllContentTitle.text = item.originalTitle
                tvSeeAllContentDesc.text = item.overview
                tvSeeAllContentDate.text = "Show time ${item.releaseDate?.convertDateFormat()}"

                ivSeeAllContentMovie.load("${Constant.BASE_URL_IMAGE_500}${item.backdropPath}") {
                    val context = root.context
                    val circularProgressDrawable = CircularProgressDrawable(context).apply {
                        strokeWidth = 5f
                        centerRadius = 30f
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
                //checkIsFavouriteSaved(item, binding)
            }
        }
    }

}
