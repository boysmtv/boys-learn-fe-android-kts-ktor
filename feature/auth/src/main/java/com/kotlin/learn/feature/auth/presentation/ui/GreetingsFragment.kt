package com.kotlin.learn.feature.auth.presentation.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.LocationUtil
import com.kotlin.learn.core.common.util.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.listener.EventListener
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
import com.kotlin.learn.core.utilities.TransactionUtil
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.auth.R
import com.kotlin.learn.feature.auth.databinding.FragmentGreetingsBinding
import com.kotlin.learn.feature.auth.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingsFragment : BaseFragment<FragmentGreetingsBinding>(FragmentGreetingsBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    private var googleSignInExt: GoogleSignInExt =
        GoogleSignInExt(
            callbackGoogleSignInSuccess = this::invokeGoogleSignInSuccess,
            callbackGoogleSignInError = this::invokeGoogleSignInError
        )

    @Inject
    lateinit var authNavigator: AuthNavigator

    private var userModel: UserModel = UserModel()

    private var token: String = Constant.EMPTY_STRING

    private val transactionId = TransactionUtil.generateTransactionID()

    private lateinit var locationUtil: LocationUtil

    private lateinit var eventListener: EventListener

    override fun setupView() {
        init()
        fetchTokenFromDatastore()
        subscribeStoreAuth()
        setupListener()
    }

    private fun subscribeStoreAuth() {
        viewModel.postUser.launch(this@GreetingsFragment) {
            when (it) {
                is Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> postUserSuccess(it.data)

                is Result.Error -> postUserError(it.throwable)
            }
        }
        viewModel.getUser.launch(this@GreetingsFragment) {
            when (it) {
                is Result.Waiting -> {}

                is Result.Loading -> showHideProgress(isLoading = true)

                is Result.Success -> getUserSuccess(it.data)

                is Result.Error -> getUserError(it.throwable)
            }
        }
    }

    private fun init() {
        googleSignInExt.initGoogle(requireContext())
        locationUtil = LocationUtil(context = requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            eventListener = context as EventListener
        } catch (_: ClassCastException) {

        }
    }
    private fun setupListener() = with(binding) {
        btnFacebook.setOnClickListener {
            showDialogGeneralError(title = "Warning", message = "Under development, please try another way")
        }

        btnGoogle.setOnClickListener {
            if (locationUtil.checkPermissions() && locationUtil.isLocationEnabled())
                onClickSignInByGoogle()
            else showWarningLocation()
        }

        btnEmail.setOnClickListener {
            if (locationUtil.checkPermissions() && locationUtil.isLocationEnabled())
                authNavigator.fromGreetingsToAuth(this@GreetingsFragment)
            else showWarningLocation()
        }
    }

    private fun showWarningLocation() {
        val content = BaseDataDialog(
            title = "Warning",
            content = "Please allow location to continue application",
            primaryButtonShow = true,
            secondaryButtonText = Constant.EMPTY_STRING,
            secondaryButtonShow = false,
            icon = R.drawable.ic_warning_rounded,
            primaryButtonText = "OK"
        )
        showDialogWithActionButton(
            dataToDialog = content,
            actionClickPrimary = {
                eventListener.askLocationPermission()
            },
            tag = RegisterFragment::class.simpleName.toString()
        )
    }

    private fun onClickSignInByGoogle() = with(googleSignInExt) {
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

    private fun invokeGoogleSignInSuccess(model: UserModel) {
        userModel = model

        /* TODO : TODO : Disable for move to firestore
        viewModel.getUser(
            UserModel().apply {
                id = userModel.idGoogle
                email = userModel.email
                method = AuthMethod.GOOGLE.name
            }
        )*/

        // TODO : Refactor fo firestore
        getUserFromFirestore()
    }

    private fun invokeGoogleSignInError(message: String) {
        showDialogGeneralError("Google sign error", message)
    }

    private fun postUserSuccess(response: BaseResponse<RegisterRespModel>) {
        showHideProgress(isLoading = false)

        invokeSpringParser(response).launch(this@GreetingsFragment) {
            when (it) {
                is SpringParser.Success -> {
                    storeToDataStore(
                        userModel.apply {
                            id = it.data?.id
                        }
                    )
                }

                is SpringParser.Error -> {
                    Log.e("Tag", "Register-ResultResponse.Error: $it")
                    showDialogGeneralError("Register Error", "Check your connection")
                }
            }
        }
    }

    private fun postUserError(throwable: Throwable) {
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

    private fun getUserSuccess(response: BaseResponse<UserModel>) {
        showHideProgress(isLoading = false)

        invokeSpringParser(response).launch(this@GreetingsFragment) { spring ->
            when (spring) {
                is SpringParser.Success -> {
                    spring.data?.let {
                        userModel = it.apply {
                            idGoogle = userModel.id
                            idToken = token
                        }
                        storeToDataStore(userModel)
                    }
                }

                is SpringParser.Error -> {
                    showDialogGeneralError("Get Profile Error", "Check your connection")
                }
            }
        }
    }

    private fun getUserError(throwable: Throwable) {
        showHideProgress(isLoading = false)

        parseResultError(throwable).launch(this@GreetingsFragment) { parser ->
            when (parser) {
                is SpringParser.Success -> {
                    viewModel.postUser(
                        userModel.apply {
                            idFireStore = Constant.EMPTY_STRING
                            phone = Constant.UNDERSCORE
                            password = Constant.UNDERSCORE
                            method = AuthMethod.GOOGLE.name
                        }
                    )
                }

                is SpringParser.Error -> {
                    showDialogGeneralError("Get profile error", throwable.message.toString())
                }
            }
        }
    }

    private fun storeToDataStore(userModel: UserModel) {
        viewModel.storeUserToDatastore(jsonUtil.toJson(userModel))
            .launch(this@GreetingsFragment) { event ->
                invokeDataStoreEvent(event,
                    isFetched = {},
                    isStored = {
                        authNavigator.fromGreetingsToHome(this@GreetingsFragment)
                    }
                )
            }
    }

    private fun fetchTokenFromDatastore() = with(viewModel) {
        fetchTokenFromDatastore().launch(this@GreetingsFragment) {
            invokeDataStoreEvent(it,
                isFetched = { message ->
                    token = message
                }, {}
            )
        }
    }

    private fun getUserFromFirestore() {
        viewModel.fetchUserFromFirestore(
            filter = hashMapOf(
                "email" to userModel.email!!,
            ),
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                userModel = it
                storeToDataStore(userModel)
            },
            onError = {
                showHideProgress(isLoading = false)
                storeUserToFirestore()
            }
        )
    }

    private fun storeUserToFirestore() {
        viewModel.storeUserToFirestore(
            id = transactionId,
            model = userModel.apply {
                id = transactionId
                idFireStore = Constant.EMPTY_STRING
                phone = Constant.UNDERSCORE
                password = Constant.UNDERSCORE
                method = AuthMethod.GOOGLE.name
            },
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                storeToDataStore(userModel)
            },
            onError = {
                showHideProgress(isLoading = false)
                showDialogGeneralError("Register failed", it)
            }
        )
    }

}