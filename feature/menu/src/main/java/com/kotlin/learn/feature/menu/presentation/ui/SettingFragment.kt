package com.kotlin.learn.feature.menu.presentation.ui

import android.util.Log
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.ProfileModel
import com.kotlin.learn.core.model.SettingModel
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.menu.databinding.FragmentSettingBinding
import com.kotlin.learn.feature.menu.presentation.viewmodel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    private val tag = this::class.java.simpleName
    private var isAutoLogin = true
    private var isSaveFavourite = true
    private var isShowNotification = true

    private val viewModel: SettingViewModel by viewModels()

    private var profileModel = ProfileModel()

    override fun setupView() {
        subscribeProfile()
        setupSettingProfile()
        fetchProfileFromFirestore()
    }

    private fun subscribeProfile() = with(viewModel) {
        profile.launch(this@SettingFragment) {
            when (it) {
                is ResultCallback.Loading -> {
                    Log.e(tag, "ResultCallback: Loading")
                }

                is ResultCallback.Success -> {
                    Log.e(tag, "ResultCallback: Success | ${it.data}")
                }

                is ResultCallback.Error -> {
                    Log.e(tag, "ResultCallback: Error | ${it.message}")
                }
            }
        }
    }

    private fun setupListener() = with(binding) {
        swAutoLogin.setOnCheckedChangeListener { _, _ ->
            isAutoLogin = !isAutoLogin
            storeAutoLoginChanges(isAutoLogin = isAutoLogin)
        }
        swSaveMovie.setOnCheckedChangeListener { _, _ ->
            isSaveFavourite = !isSaveFavourite
            storeAutoLoginChanges(isSaveFavourite = isSaveFavourite)
        }
        swNotification.setOnCheckedChangeListener { _, _ ->
            isShowNotification = !isShowNotification
            storeAutoLoginChanges(isShowNotification = isShowNotification)
        }
    }

    private fun setupSettingProfile() = with(viewModel) {
        fetchProfileFromDatastore().launch(this@SettingFragment) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = {
                    it?.let { model ->
                        setupSwitchListener(model)
                    }
                },
                isError = {},
                isStored = {}
            )
        }
    }

    private fun storeAutoLoginChanges(
        isAutoLogin: Boolean? = null,
        isSaveFavourite: Boolean? = null,
        isShowNotification: Boolean? = null
    ) = with(viewModel) {
        fetchProfileFromDatastore().launch(this@SettingFragment) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = {
                    it?.let { model ->
                        profileModel = model
                        storeProfileToDatastore(
                            jsonUtil.toJson(
                                model.apply {
                                    setting?.apply {
                                        if (isAutoLogin != null) login = isAutoLogin
                                        if (isSaveFavourite != null) favourite = isSaveFavourite
                                        if (isShowNotification != null) notification = isShowNotification
                                    }
                                    if (setting == null) {
                                        SettingModel().apply {
                                            if (isAutoLogin != null) login = isAutoLogin
                                            if (isSaveFavourite != null) favourite = isSaveFavourite
                                            if (isShowNotification != null) notification = isShowNotification
                                        }
                                    }
                                }
                            )
                        )
                    }
                },
                isError = {},
                isStored = {}
            )
        }
    }

    private fun setupSwitchListener(model: ProfileModel) = with(binding) {
        model.setting?.let { model ->
            model.login.let {
                isAutoLogin = it
                swAutoLogin.isChecked = it
            }
            model.favourite.let {
                isSaveFavourite = it
                swSaveMovie.isChecked = it
            }
            model.notification.let {
                isShowNotification = it
                swNotification.isChecked = it
            }
        }

        setupListener()
    }

    private fun fetchProfileFromFirestore() {
        viewModel.fetchProfileFromFirestore(
            filter = Pair("id", "0DK4b7ffc2b9e6807C0Y")
        )
    }

}
