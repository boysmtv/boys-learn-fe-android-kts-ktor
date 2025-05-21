package com.kotlin.learn.feature.splash.presentation.ui

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import com.kotlin.learn.core.common.base.BaseFragment
import com.kotlin.learn.core.common.google.GoogleSignInExt
import com.kotlin.learn.core.common.util.event.invokeDataStoreEvent
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.nav.navigator.ParentNavigator
import com.kotlin.learn.core.utilities.Constant
import com.kotlin.learn.core.utilities.Constant.EMPTY_STRING
import com.kotlin.learn.core.utilities.Constant.ONE_THOUSAND_LONG
import com.kotlin.learn.core.utilities.extension.launch
import com.kotlin.learn.feature.common.viewmodel.UserViewModel
import com.kotlin.learn.feature.splash.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var parentNavigator: ParentNavigator

    private val userViewModel: UserViewModel by viewModels()

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt({}, {})

    override fun setupView() {
        init()
        subscribeUser()
        setupSplash()
    }

    private fun init() {
        googleSignInExt.initGoogle(requireContext())
    }

    private fun subscribeUser() {
        with(userViewModel) {
            fetchUserFirestore.launch(this@SplashFragment) {
                when (it) {
                    is ResultCallback.Loading -> {
                        // show loading
                    }

                    is ResultCallback.Success -> {
                        updateUserToDataStore(it.data)
                    }

                    is ResultCallback.Error -> {
                        showDialogGeneralError("Warning", "Error fetch user, ${it.message}")
                    }
                }
            }
        }
    }

    private fun setupSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            fetchUserFromDataStore()
        }, ONE_THOUSAND_LONG)
    }

    private fun fetchUserFromDataStore() = with(userViewModel) {
        fetchUserFromDatastore().launch(this@SplashFragment) { event ->
            invokeDataStoreEvent(
                event = event,
                isFetched = {
                    if (it.displayName != EMPTY_STRING)
                        if (it.profile?.setting?.login == true){
                            fetchUserFromFirestore(it)
                        }
                        else
                            navigateToGreetings()
                    else
                        navigateToGreetings()
                },
                isError = {
                    navigateToGreetings()
                },
                isStored = {}
            )
        }
    }

    private fun fetchUserFromFirestore(it: UserModel) {
        userViewModel.fetchUserFromFirestoreAsync(
            filter = Pair("email", it.email ?: EMPTY_STRING)
        )
    }

    private fun updateUserToDataStore(it: UserModel) = with(userViewModel) {
        storeUserToDatastore(jsonUtil.toJson(it)).launch(this@SplashFragment) { event ->
            invokeDataStoreEvent(event,
                isStored = {
                    navigationToMenu()
                }
            )
        }
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