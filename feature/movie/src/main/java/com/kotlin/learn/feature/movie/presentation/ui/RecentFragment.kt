package com.kotlin.learn.feature.movie.presentation.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.model.FavouriteDataModel
import com.kotlin.learn.core.model.RecentDataModel
import com.kotlin.learn.core.nav.navigator.ParentNavigator
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.core.utilities.hide
import com.kotlin.learn.core.utilities.show
import com.kotlin.learn.feature.common.viewmodel.UserViewModel
import com.kotlin.learn.feature.movie.adapter.RecentAdapter
import com.kotlin.learn.feature.movie.databinding.FragmentRecentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecentFragment : BaseFragment<FragmentRecentBinding>(FragmentRecentBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    private val recentAdapter = RecentAdapter(this::onMovieClicked)

    @Inject
    lateinit var parentNavigator: ParentNavigator

    override fun setupView() {
        setupAdapter()
        subscribeUser()
    }

    private fun subscribeUser() = with(viewModel) {
        fetchUserFromDatastore().launch(this@RecentFragment) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = {
                    setupSubmitAdapter(it.recent)
                },
            )
        }
    }

    private fun setupAdapter() = with(binding) {
        rvRecent.apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = recentAdapter
        }
    }

    private fun setupSubmitAdapter(recent: MutableList<RecentDataModel>) = with(binding) {
        recentAdapter.submitData(recent)
        updateUiOrEmptyList(recent)
    }

    private fun updateUiOrEmptyList(favourite: MutableList<RecentDataModel>) = with(binding) {
        if (favourite.isNotEmpty()) {
            tvEmptyRecent.hide()
            rvRecent.show()
        } else {
            tvEmptyRecent.show()
            rvRecent.hide()
        }
    }

    private fun onMovieClicked(item: RecentDataModel) {
        parentNavigator.fromFavouriteToDetail(
            fragment = this,
            movieId = item.id.toString()
        )
    }

}