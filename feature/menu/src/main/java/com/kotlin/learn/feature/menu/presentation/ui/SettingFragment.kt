package com.kotlin.learn.feature.menu.presentation.ui

import android.util.Log
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.SettingModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.common.viewmodel.UserViewModel
import com.kotlin.learn.feature.menu.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    private val tag = this::class.java.simpleName
    private var isAutoLogin = true
    private var isSaveFavourite = true
    private var isShowNotification = true

    private val userViewModel: UserViewModel by viewModels()

    override fun setupView() {
        subscribeProfile()
        fetchUserSetting()
    }

    private fun subscribeProfile() = with(userViewModel) {
        fetchUserFirestore.launch(this@SettingFragment) {
            when (it) {
                is ResultCallback.Loading -> {
                    Log.e(tag, "ResultCallback: Loading")
                }

                is ResultCallback.Success -> {
                    Log.e(tag, "ResultCallback: Success")
                }

                is ResultCallback.Error -> {
                    Log.e(tag, "ResultCallback: Error")
                }
            }
        }
    }

    private fun setupListener() = with(binding) {
        swAutoLogin.setOnCheckedChangeListener { _, _ ->
            isAutoLogin = !isAutoLogin
            setupSwitchedChanges(isAutoLogin = isAutoLogin)
        }
        swSaveMovie.setOnCheckedChangeListener { _, _ ->
            isSaveFavourite = !isSaveFavourite
            setupSwitchedChanges(isSaveFavourite = isSaveFavourite)
        }
        swNotification.setOnCheckedChangeListener { _, _ ->
            isShowNotification = !isShowNotification
            setupSwitchedChanges(isShowNotification = isShowNotification)
        }
    }

    private fun fetchUserSetting() = with(userViewModel) {
        fetchUserFromDatastore().launch(this@SettingFragment) { dataStoreCacheEvent ->
            invokeDataStoreEvent(dataStoreCacheEvent,
                isFetched = {
                    setupSwitchListener(it)
                },
                isError = {},
                isStored = {}
            )
        }
    }

    private fun setupSwitchedChanges(
        isAutoLogin: Boolean? = null,
        isSaveFavourite: Boolean? = null,
        isShowNotification: Boolean? = null
    ) {
        userViewModel.fetchUserFromDatastore().launch(this@SettingFragment) { dataStoreCacheEvent ->
            invokeDataStoreEvent(dataStoreCacheEvent,
                isFetched = {
                    it.apply {
                        profile?.setting?.apply {
                            if (isAutoLogin != null) login = isAutoLogin
                            if (isSaveFavourite != null) favourite = isSaveFavourite
                            if (isShowNotification != null) notification = isShowNotification
                        }
                        if (profile?.setting == null) {
                            SettingModel().apply {
                                if (isAutoLogin != null) login = isAutoLogin
                                if (isSaveFavourite != null) favourite = isSaveFavourite
                                if (isShowNotification != null) notification = isShowNotification
                            }
                        }
                    }
                    storeSwitchedChanges(it)
                },
                isError = {},
                isStored = {}
            )
        }
    }

    private fun storeSwitchedChanges(it: UserModel) = with(userViewModel) {
        storeUserToDatastore(jsonUtil.toJson(it)).launch(this@SettingFragment) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = {},
                isError = {},
                isStored = {
                    it.id?.let { id ->
                        it.profile?.let { profile ->
                            updateUserFromFirestoreAsync(
                                id = id,
                                model = mapOf(
                                    "profile" to profile
                                )
                            )
                        }
                    }

                },
            )
        }
    }

    private fun setupSwitchListener(model: UserModel) = with(binding) {
        model.profile?.setting?.let { model ->
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
        fetchProfileFromFirestore(model)
    }

    private fun fetchProfileFromFirestore(model: UserModel) {
        model.email?.let {
            userViewModel.fetchUserFromFirestoreAsync(
                filter = Pair("email", it)
            )
        }
    }

}
