package com.kotlin.learn.feature.movie.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.FIFTEEN
import com.kotlin.learn.core.utilities.Constant.FIVE
import com.kotlin.learn.core.utilities.Constant.ONE
import com.kotlin.learn.core.utilities.Constant.SEVEN
import com.kotlin.learn.core.utilities.Constant.SEVENTY_FIVE
import com.kotlin.learn.core.utilities.Constant.THREE
import com.kotlin.learn.core.utilities.Constant.TWO
import com.kotlin.learn.core.utilities.Constant.ZERO
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.BannerHomeItemBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import jp.wasabeef.glide.transformations.BlurTransformation

typealias OnClickBannerMovie = (MovieDataModel) -> Unit

class MovieBannerAdapter(private val onClickBannerMovie: OnClickBannerMovie) : BaseBannerAdapter<MovieDataModel>() {

    override fun bindData(
        holder: BaseViewHolder<MovieDataModel>,
        item: MovieDataModel?,
        position: Int,
        pageSize: Int
    ) {
        if (holder is ViewHolder && item != null) {
            with(holder.viewBinding) {
                val posterUrl = "${Constant.BASE_URL_IMAGE_500}${item.posterPath}"

                Glide.with(ivItemHomeThumbnail.context)
                    .load(posterUrl)
                    .into(ivItemHomeThumbnail)

                Glide.with(ivItemHomeBlur.context)
                    .load(posterUrl)
                    .apply(bitmapTransform(BlurTransformation(SEVENTY_FIVE, THREE)))
                    .into(ivItemHomeBlur)

                val isShortTitle = (item.originalTitle?.length ?: ZERO) <= FIFTEEN
                tvItemHomeTitle.maxLines = if (isShortTitle) ONE else TWO
                tvItemHomeDesc.maxLines = if (isShortTitle) SEVEN else FIVE

                tvItemHomeTitle.text = item.originalTitle
                tvItemHomeDesc.text = item.overview

                btnClickHere.setOnClickListener {
                    onClickBannerMovie(item)
                }
            }
        }
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_home_item
    }

    override fun createViewHolder(
        parent: ViewGroup,
        itemView: View,
        viewType: Int
    ): BaseViewHolder<MovieDataModel> {
        return ViewHolder(BannerHomeItemBinding.bind(itemView))
    }

    inner class ViewHolder(var viewBinding: BannerHomeItemBinding) :
        BaseViewHolder<MovieDataModel>(viewBinding.root)
}
