package com.kotlin.learn.feature.auth.presentation.ui

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.Result
import com.kotlin.learn.core.common.util.network.SpringParser
import com.kotlin.learn.core.common.util.network.invokeSpringParser
import com.kotlin.learn.core.common.util.network.parseResultError
import com.kotlin.learn.core.model.AuthMethod
import com.kotlin.learn.core.model.BaseResponse
import com.kotlin.learn.core.model.RegisterRespModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.AuthNavigator
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialog
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.R
import com.kotlin.learn.feature.auth.databinding.FragmentGreetingsBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.GreetingsViewModel
import com.kotlin.learn.feature.auth.presentation.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingsFragment : BaseFragment<FragmentGreetingsBinding>(FragmentGreetingsBinding::inflate) {

    private val viewModel: GreetingsViewModel by viewModels()

    private val viewModelRegister: RegisterViewModel by viewModels()

    private var googleSignInExt: GoogleSignInExt =
        GoogleSignInExt(
            resultDataAuthSuccess = this::invokeResultDataAuthSuccess,
            resultDataAuthError = this::invokeResultDataAuthError
        )

    @Inject
    lateinit var authNavigator: AuthNavigator

    @Inject
    lateinit var jsonUtil: JsonUtil

    private var userModel: UserModel = UserModel()

    override fun setupView() {
        init()
        subscribeStoreAuth()
        setupListener()
    }

    private fun subscribeStoreAuth() {
        viewModelRegister.register.launch(this@GreetingsFragment) {
            when (it) {
                Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> parseRegisterSuccess(it.data)

                is Result.Error -> parseRegisterError(it.throwable)
            }
        }

        viewModel.storeFirebase.launch(this@GreetingsFragment) {

        }
    }

    private fun init() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun setupListener() = with(binding) {
        btnFacebook.setOnClickListener {

        }
        btnGoogle.setOnClickListener {
            signInGoogle()
        }
        btnEmail.setOnClickListener {
            authNavigator.fromGreetingsToAuth(this@GreetingsFragment)
        }
    }

    // start region google login
    private fun signInGoogle() = with(googleSignInExt) {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityIntent.launch(signInIntent)
    }

    private var startActivityIntent: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        it?.let {
            with(googleSignInExt) {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                handleResult(task)
            }
        }
    }

    private fun invokeResultDataAuthSuccess(model: UserModel) {
        viewModelRegister.postRegister(
            userModel.apply {
                idFireStore = Constant.EMPTY_STRING
                idGoogle = model.idGoogle
                idToken = Constant.EMPTY_STRING
                firstName = model.firstName
                lastName = model.lastName
                displayName = model.displayName
                email = model.email
                phone = Constant.UNDERSCORE
                photoUrl = model.photoUrl
                password = Constant.UNDERSCORE
                method = AuthMethod.GOOGLE.name
            }
        )
    }

    private fun invokeResultDataAuthError(message: String) {
        Log.e("Greetings", "Error Invoke Data Auth - Msg : $message")
        Toast.makeText(context, "Error Invoke Data Auth - Msg : $message", Toast.LENGTH_LONG).show()
    }
    // end region google login


    private fun parseRegisterSuccess(response: BaseResponse<RegisterRespModel>) {
        showHideProgress(isLoading = false)

        invokeSpringParser(response).launch(this@GreetingsFragment) {
            when (it) {
                is SpringParser.Success -> {
                    viewModel.storeUserToDatastore(
                        jsonUtil.toJson(
                            userModel.apply {
                                id = it.data?.id ?: Constant.EMPTY_STRING
                            }
                        )
                    ).launch(this@GreetingsFragment) { event ->
                        invokeDataStoreEvent(event,
                            isFetched = {},
                            isStored = {
                                authNavigator.fromGreetingsToHome(this@GreetingsFragment)
                            }
                        )
                    }

                }

                is SpringParser.Error -> {
                    Log.e("Tag", "Register-ResultResponse.Error: $it")
                    showDialogGeneralError("Register Error", "Check your connection")
                }
            }
        }
    }

    private fun parseRegisterError(throwable: Throwable) {
        showHideProgress(isLoading = false)

        parseResultError(throwable).launch(this@GreetingsFragment) { parser ->
            when (parser) {
                is SpringParser.Success -> {
                    showDialogGeneralError("Register failed", parser.data.toString())
                }

                is SpringParser.Error -> {
                    showDialogGeneralError("Register error", throwable.message.toString())
                }
            }
        }
    }

}