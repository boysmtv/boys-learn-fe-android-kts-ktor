package com.kotlin.learn.feature.movie.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.kotlin.learn.core.model.MovieDataModel
import com.kotlin.learn.core.utilities.Constant
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
        if (holder is ViewHolder) {
            with(holder.viewBinding) {
                item?.let {
                    Glide.with(ivItemHomeThumbnail.context)
                        .load("${Constant.BASE_URL_IMAGE_500}${item.posterPath}")
                        .into(ivItemHomeThumbnail)
                    Glide.with(ivItemHomeBlur.context)
                        .load("${Constant.BASE_URL_IMAGE_500}${item.posterPath}")
                        .apply(bitmapTransform(BlurTransformation(75, 3)))
                        .into(ivItemHomeBlur)

                    if (it.originalTitle?.length!! <= 15) {
                        tvItemHomeTitle.maxLines = 1
                        tvItemHomeDesc.maxLines = 7
                    } else {
                        tvItemHomeTitle.maxLines = 2
                        tvItemHomeDesc.maxLines = 5
                    }

                    tvItemHomeTitle.text = it.originalTitle
                    tvItemHomeDesc.text = it.overview

                    btnClickHere.setOnClickListener { _ ->
                        onClickBannerMovie(it)
                    }
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
