package com.kotlin.learn.feature.auth.util.common

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kotlin.learn.core.common.util.security.SecurePrefManager
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.core.utilities.PreferenceConstants
import com.kotlin.learn.feature.auth.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class GoogleSignInExt(private val context: Context) {

    @Inject
    lateinit var securePrefManager: SecurePrefManager

    @Inject
    lateinit var moshi: Moshi

    var googleSignInClient: GoogleSignInClient
    val signInReqCore: Int = 123
    private var firebaseAuth: FirebaseAuth

    private val signInAdapter: JsonAdapter<AuthGoogleSignInModel> by lazy {
        moshi.adapter(AuthGoogleSignInModel::class.java)
    }

    init {
        FirebaseApp.initializeApp(context)

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, signInOptions)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                updateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val authGoogleSignInModel = AuthGoogleSignInModel(
                    account = account.account.toString(),
                    id = account.id.toString(),
                    idToken = account.idToken.toString(),
                    email = account.email.toString(),
                    givenName = account.givenName.toString(),
                    familyName = account.familyName.toString(),
                    displayName = account.displayName.toString(),
                    isExpired = account.isExpired.toString(),
                    photoUrl = account.photoUrl.toString(),
                    grantedScopes = account.grantedScopes.toString()
                )
                securePrefManager.setString(
                    PreferenceConstants.Auth.PREF_GOOGLE_AUTH, signInAdapter.toJson(
                        authGoogleSignInModel
                    )
                )

                Log.e("TAG", "BOYS-Model : $authGoogleSignInModel")
            }
        }
    }

    private fun getGoogleSingInClient(): GoogleSignInClient {
        /**
         * Configure sign-in to request the user's ID, email address, and basic
         * profile. ID and basic profile are included in DEFAULT_SIGN_IN.
         */
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()
        /**
         * Build a GoogleSignInClient with the options specified by gso.
         */
        return GoogleSignIn.getClient(context, gso);
    }

    private fun isUserSignedIn(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return account != null
    }

    private fun signOut() {
        if (isUserSignedIn()) {
            getGoogleSingInClient().signOut().addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, " Signed out ", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, " Error ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}