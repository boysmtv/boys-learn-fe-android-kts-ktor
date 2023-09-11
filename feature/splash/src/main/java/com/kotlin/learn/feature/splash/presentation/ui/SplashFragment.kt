package com.kotlin.learn.feature.splash.presentation.ui

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.InternetUtil
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.common.util.LocationUtil
import com.kotlin.learn.core.common.util.NotificationUtil
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.model.PermissionModel
import com.kotlin.learn.core.model.ProfileModel
import com.kotlin.learn.core.nav.navigator.ParentNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.common.util.TransactionUtil
import com.kotlin.learn.core.model.SettingModel
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.splash.databinding.FragmentSplashBinding
import com.kotlin.learn.feature.splash.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val tag = this::class.java.simpleName

    private val viewModel: SplashViewModel by viewModels()

    @Inject
    lateinit var parentNavigator: ParentNavigator

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt({}, {})

    private var profileModel = ProfileModel()

    override fun setupView() {
        init()
        setupTransactionID()
        setupListener()
    }

    private fun init() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun setupTransactionID(){
        viewModel.storeDeviceIdToFirestore(TransactionUtil.generateTransactionID())
    }

    private fun setupListener() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.fetchUserFromDatastore().launch(this) { event ->
                invokeDataStoreEvent(event,
                    isFetched = { data ->
                        data?.let {
                            setupProfile(
                                isUserExist = it.displayName != Constant.EMPTY_STRING,
                                userModel = it
                            )
                        }
                    },
                    isError = {
                        setupProfile(
                            isUserExist = false,
                            userModel = UserModel()
                        )
                    }, {}
                )
            }
        }, 100)
    }

    private fun setupProfile(isUserExist: Boolean, userModel: UserModel) {
        profileModel.apply {
            id = TransactionUtil.generateTransactionID()
            userId = userModel.id ?: Constant.EMPTY_STRING
            connection = InternetUtil(requireContext()).getStatusConnectionModel()
            permission = PermissionModel().apply {
                location = LocationUtil(requireContext()).checkPermissions()
                internet = InternetUtil(requireContext()).isNetworkAvailable()
                notification = NotificationUtil(requireContext()).isNotificationEnabled()
            }
            setting = SettingModel(
                login = true,
                favourite = true,
                notification = true
            )
            updatedAt = TransactionUtil.getTimestampWithFormat()
            createdAt = updatedAt
        }

        if (isUserExist) {
            viewModel.storeProfileToDatastore(jsonUtil.toJson(profileModel))
                .launch(this@SplashFragment) { event ->
                    invokeDataStoreEvent(event,
                        {}, {},
                        isStored = {
                            viewModel.storeProfileToFirestore(
                                id = profileModel.id ?: TransactionUtil.generateTransactionID(),
                                model = profileModel,
                                onError = {
                                    showDialogGeneralError("Warning", it)
                                },
                                onLoad = {
                                },
                                onSuccess = {
                                    navigationToMenu()
                                }
                            )

                        }
                    )
                }
        } else navigateToGreetings()
    }

    private fun navigationToMenu() {
        parentNavigator.fromSplashToMenu(this@SplashFragment)
    }

    private fun navigateToGreetings() {
        googleSignOut()
        parentNavigator.fromSplashToGreetings(this@SplashFragment)
    }

    private fun googleSignOut() = googleSignInExt.signOut({}, {})

}