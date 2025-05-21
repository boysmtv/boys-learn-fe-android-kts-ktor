package com.kotlin.learn.feature.movie.presentation.ui

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.model.FavouriteDataModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.ParentNavigator
import com.kotlin.learn.core.utilities.Constant.THREE
import com.kotlin.learn.core.utilities.Constant.THREE_THOUSAND_LONG
import com.kotlin.learn.core.utilities.Constant.TWO_THOUSAND_FIFTY_LONG
import com.kotlin.learn.core.utilities.Constant.ZERO
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.core.utilities.hide
import com.kotlin.learn.core.utilities.show
import com.kotlin.learn.feature.common.viewmodel.UserViewModel
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.adapter.FavouriteAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    private val favouriteAdapter = FavouriteAdapter(this::onMovieClicked)

    @Inject
    lateinit var parentNavigator: ParentNavigator

    lateinit var favouriteModelToRemove: MutableList<FavouriteDataModel>

    override fun setupView() {
        setupAdapter()
        subscribeUser()
        setupListener()
    }

    private fun subscribeUser() = with(viewModel) {
        fetchUserFromDatastore().launch(this@FavouriteFragment) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = {
                    favouriteModelToRemove = it.favourite
                    setupSubmitAdapter(it.favourite)
                },
            )
        }
    }

    private fun setupAdapter() = with(binding) {
        rvFavourite.apply {
            layoutManager = GridLayoutManager(activity, THREE)
            adapter = favouriteAdapter
        }
    }

    private fun setupListener() = with(binding) {
        ivDelete.setOnClickListener {
            viewModel.fetchUserFromDatastore().launch(this@FavouriteFragment) { event ->
                invokeDataStoreEvent(
                    event,
                    isFetched = {
                        updateUserFavouriteToDatastore(it)
                    },
                )
            }
        }
    }

    private fun updateUserFavouriteToDatastore(model: UserModel) {
        viewModel.storeUserToDatastore(
            jsonUtil.toJson(
                model.apply {
                    favourite = ArrayList()
                }
            )
        ).launch(this@FavouriteFragment) { event ->
            invokeDataStoreEvent(
                event,
                isStored = {
                    updateUserFavouriteToFirestore(model)
                },
            )
        }
    }

    private fun updateUserFavouriteToFirestore(model: UserModel) {
        model.id?.let { id ->
            viewModel.updateUserToFirestore(
                id = id,
                model = mapOf(
                    "favourite" to model.favourite
                ),
                onLoad = { },
                onSuccess = {
                    removeAllItemListFavourite()
                },
                onError = { }
            )
        }
    }

    private fun setupSubmitAdapter(favourite: MutableList<FavouriteDataModel>) = with(binding) {
        favouriteAdapter.submitData(favourite)
        updateUiOrEmptyList(favourite)
    }

    private fun updateUiOrEmptyList(favourite: MutableList<FavouriteDataModel>) = with(binding) {
        if (favourite.isNotEmpty()) {
            tvEmptyFavourite.hide()
            rvFavourite.show()
        } else {
            tvEmptyFavourite.show()
            rvFavourite.hide()
        }
    }

    private fun onMovieClicked(item: FavouriteDataModel) {
        parentNavigator.fromFavouriteToDetail(
            fragment = this,
            movieId = item.id.toString()
        )
    }

    private fun removeAllItemListFavourite() {
        var mStopHandler = false
        val handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {
                if (favouriteModelToRemove.size == ZERO) {
                    mStopHandler = true
                }
                if (!mStopHandler) {
                    val v: View = binding.rvFavourite.findViewHolderForAdapterPosition(ZERO)!!.itemView
                    removeItemFavourite(v, favouriteModelToRemove)
                } else {
                    handler.removeCallbacksAndMessages(null)
                }
                handler.postDelayed(this, TWO_THOUSAND_FIFTY_LONG)
            }
        }
        requireActivity().runOnUiThread(runnable)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun removeItemFavourite(rowView: View, favourite: MutableList<FavouriteDataModel>) {
        val anim: Animation = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slide_out_right
        )
        anim.duration = THREE_THOUSAND_LONG
        rowView.startAnimation(anim)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            if (favourite.size == ZERO) {
                updateUiOrEmptyList(favourite)
                return@Runnable
            }
            favourite.removeAt(ZERO)
            favouriteAdapter.notifyDataSetChanged()
        }, anim.duration)
    }
}