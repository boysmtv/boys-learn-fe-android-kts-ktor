package com.kotlin.learn.feature.movie.presentation.ui

import android.graphics.Paint
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.movie.R
import com.kotlin.learn.feature.movie.databinding.FragmentSettingBinding
import com.kotlin.learn.feature.movie.presentation.viewmodel.SettingViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {

    @Inject
    lateinit var jsonUtil: JsonUtil

    private val viewModel: SettingViewModel by viewModels()

    override fun setupView() {
        loadProfile()
        loadToken()
        setupListener()
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
                    Log.e("loadToken", "SettingFragment - Your token : $message")
                }, {}
            )
        }
    }

    private fun setupListener() = with(binding) {
        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun updateUi(message: String) = with(binding) {
        jsonUtil.fromJson<AuthGoogleSignInModel>(message)?.let {
            etFirstName.setText(it.givenName)
            etLastName.setText(it.familyName)
            etEmail.setText(it.email)

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