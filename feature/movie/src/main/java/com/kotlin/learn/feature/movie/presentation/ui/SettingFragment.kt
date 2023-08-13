package com.kotlin.learn.feature.movie.presentation.ui

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.FragmentSettingBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    private val viewModel: SettingViewModel by viewModels()

    @Inject
    lateinit var authNavigator: AuthNavigator

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt({}, {})

    override fun setupView() {
        init()
        loadProfile()
        loadToken()
        setupListener()
    }

    private fun init() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun loadProfile() = with(viewModel) {
        fetchDataAuth().launch(this@SettingFragment) {
            invokeDataStoreEvent(it,
                isFetched = { message ->
                    updateUi(message)
                }, {}
            )
        }
    }

    private fun loadToken() = with(viewModel) {
        fetchDataTokenFcm().launch(this@SettingFragment) {
            invokeDataStoreEvent(it,
                isFetched = { message ->
                    Log.e("loadToken", "SettingFragment - Your token DataStore: $message")
                }, {}
            )
        }

        binding.etToken.apply {
            setText(
                context?.getSharedPreferences(
                    "PREFERENCE_NAME", Context.MODE_PRIVATE
                )?.getString(PreferenceConstants.Authorization.PREF_FCM_TOKEN, "")
            )
        }
        Log.e(
            "loadToken", "SettingFragment - Your token Preferences: ${
                context?.getSharedPreferences(
                    "PREFERENCE_NAME", Context.MODE_PRIVATE
                )?.getString(PreferenceConstants.Authorization.PREF_FCM_TOKEN, "")
            }"
        )
    }

    private fun setupListener() = with(binding) {
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnLogout.setOnClickListener {
            googleSignInExt.signOut(
                isSuccess = {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ "Logout is success",
                        /* duration = */ Toast.LENGTH_SHORT
                    ).show()
                },
                isError = {
                    Toast.makeText(
                        /* context = */ requireContext(),
                        /* text = */ "Logout is error : $it",
                        /* duration = */ Toast.LENGTH_LONG
                    ).show()
                }
            )
            viewModel.clearPreferences()
            authNavigator.fromSettingToGreetings(this@SettingFragment)
        }
    }

    private fun updateUi(message: String) = with(binding) {
        jsonUtil.fromJson<UserModel>(message)?.let {
            if (it.firstName != Constant.EMPTY_STRING) {
                etFirstName.setText(it.firstName)
                etLastName.setText(it.lastName)
                etEmail.setText(it.email)
            } else {
                val displayName = it.displayName.split(" ")
                if (displayName.size > 1) {
                    etFirstName.setText(displayName[0])
                    etLastName.setText(displayName[1])
                } else etFirstName.setText(displayName[0])
            }

            ivImage.load(it.photoUrl) {
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
        }
    }

}