package com.kotlin.learn.catalog.movie.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.BannerHomeItemBinding
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
        if (holder is ViewBindingViewHolder) {
            item?.let {
                Glide.with(holder.viewBinding.ivItemHomeThumbnail.context)
                    .load("${Constant.BASE_URL_IMAGE}${item.posterPath}")
                    .into(holder.viewBinding.ivItemHomeThumbnail)
                Glide.with(holder.viewBinding.ivItemHomeBlur.context)
                    .load("${Constant.BASE_URL_IMAGE}${item.posterPath}")
                    .apply(bitmapTransform(BlurTransformation(75, 3)))
                    .into(holder.viewBinding.ivItemHomeBlur)
            }

            with(holder.viewBinding) {
                btnClickHere.setOnClickListener {
                    item?.let { model -> onClickBannerMovie(model) }
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
        return ViewBindingViewHolder(BannerHomeItemBinding.bind(itemView))
    }

    inner class ViewBindingViewHolder(var viewBinding: BannerHomeItemBinding) :
        BaseViewHolder<MovieDataModel>(viewBinding.root)
}
