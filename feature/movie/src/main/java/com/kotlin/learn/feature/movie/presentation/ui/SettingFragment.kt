package com.kotlin.learn.feature.movie.presentation.ui

import android.graphics.Paint
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.model.AuthMethod
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.utilities.Constant
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

    private lateinit var userModel: UserModel

    override fun setupView() {
        init()
        loadProfile()
        loadToken()
        setupListener()
    }

    private fun init() {
        userModel = UserModel()
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
                    binding.etToken.apply {
                        setText(message)
                    }
                }, {}
            )
        }
    }

    private fun setupListener() = with(binding) {
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnLogout.setOnClickListener {
            if (userModel.method == AuthMethod.GOOGLE.name)
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
            userModel = it

            if (it.firstName != Constant.EMPTY_STRING) {
                etFirstName.setText(it.firstName)
                etLastName.setText(it.lastName)
                etEmail.setText(it.email)
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