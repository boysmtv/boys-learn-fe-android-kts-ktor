package com.kotlin.learn.catalog.movie.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.kotlin.learn.catalog.core.model.MovieDataModel
import com.kotlin.learn.catalog.core.utilities.Constant
import com.kotlin.learn.catalog.feature.movie.R
import com.kotlin.learn.catalog.feature.movie.databinding.BannerItemHomeBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlin.math.roundToInt


class MovieBannerAdapter : BaseBannerAdapter<MovieDataModel>() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun bindData(
        holder: BaseViewHolder<MovieDataModel>,
        data: MovieDataModel?,
        position: Int,
        pageSize: Int
    ) {
        if (holder is ViewBindingViewHolder) {
            data?.let {
                Glide.with(holder.viewBinding.ivItemHomeThumbnail.context)
                    .load("${Constant.BASE_URL_IMAGE}${data.posterPath}")
                    .into(holder.viewBinding.ivItemHomeThumbnail)
                Glide.with(holder.viewBinding.ivItemHomeBlur.context)
                    .load("${Constant.BASE_URL_IMAGE}${data.posterPath}")
                    .apply(bitmapTransform(BlurTransformation(75, 3)))
                    .into(holder.viewBinding.ivItemHomeBlur)
            }
//
//            // just for android S fakyou
//            holder.viewBinding.ivItemHomeBlur.setRenderEffect(
//                RenderEffect.createBlurEffect(
//                    60.0f, 60.0f, Shader.TileMode.CLAMP
//                )
//            )
        }
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_item_home
    }

    override fun createViewHolder(
        parent: ViewGroup,
        itemView: View,
        viewType: Int
    ): BaseViewHolder<MovieDataModel> {
        return ViewBindingViewHolder(BannerItemHomeBinding.bind(itemView))
    }

    inner class ViewBindingViewHolder(var viewBinding: BannerItemHomeBinding) :
        BaseViewHolder<MovieDataModel>(viewBinding.root)

    object BlurBuilder {
        private const val BITMAP_SCALE = 0.4f
        private const val BLUR_RADIUS = 7.5f

        fun blur(context: Context?, image: Bitmap): Bitmap {
            val width = (image.width * BITMAP_SCALE).roundToInt()
            val height = (image.height * BITMAP_SCALE).roundToInt()
            val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
            val outputBitmap = Bitmap.createBitmap(inputBitmap)
            val rs = RenderScript.create(context)
            val theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
            val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
            theIntrinsic.setRadius(BLUR_RADIUS)
            theIntrinsic.setInput(tmpIn)
            theIntrinsic.forEach(tmpOut)
            tmpOut.copyTo(outputBitmap)
            return outputBitmap
        }
    }
}
