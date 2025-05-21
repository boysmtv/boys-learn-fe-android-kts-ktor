package com.kotlin.learn.feature.movie.presentation.ui

import android.graphics.Paint
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.model.AuthMethod
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.MenuNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.FIVE_FLOAT
import com.kotlin.learn.core.utilities.Constant.THIRTY_FLOAT
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.FragmentProfileBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: SettingViewModel by viewModels()

    @Inject
    lateinit var menuNavigator: MenuNavigator

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt({}, {})

    private lateinit var userModel: UserModel

    private var token: String = Constant.EMPTY_STRING

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
        fetchDataAuth().launch(this@ProfileFragment) { event ->
            invokeDataStoreEvent(event,
                isFetched = { data ->
                    data?.let {
                        updateUi(it)
                    }
                },
            )
        }
    }

    private fun loadToken() = with(viewModel) {
        fetchDataTokenFcm().launch(this@ProfileFragment) {event ->
            invokeDataStoreEvent(event,
                isFetched = { message ->
                    binding.etToken.apply {
                        setText(message)
                    }
                    token = message
                },
            )
        }
    }

    private fun setupListener() = with(binding) {

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
            viewModel.clearPreferences(token)
            menuNavigator.fromMenuToGreetings(requireActivity())
        }

    }

    private fun updateUi(model: UserModel) = with(binding) {
        userModel = model

        if (model.firstName != Constant.EMPTY_STRING) {
            etFirstName.setText(model.firstName)
            etLastName.setText(model.lastName)
            etEmail.setText(model.email)
        }

        ivImage.load(model.photoUrl) {
            val context = root.context
            val circularProgressDrawable = CircularProgressDrawable(context).apply {
                strokeWidth = FIVE_FLOAT
                centerRadius = THIRTY_FLOAT
                strokeCap = Paint.Cap.BUTT
                start()
            }
            placeholder(circularProgressDrawable)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }

}