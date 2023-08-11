package com.kotlin.learn.core.common.google

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
import com.kotlin.learn.core.common.R
import com.kotlin.learn.core.model.UserModel

class GoogleSignInExt(
    private val resultDataAuthSuccess: (UserModel) -> Unit,
    private val resultDataAuthError: (String) -> Unit,
) {
    private lateinit var context: Context

    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    fun initGoogle(context: Context) {
        this.context = context
        FirebaseApp.initializeApp(context)

        googleSignInClient = GoogleSignIn.getClient(
            context, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        )
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                handleDataAuth(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            Log.e("", "Error Auth: $e")
            Log.e("", "Error Auth-message: ${e.message}")
            Log.e("", "Error Auth-status: ${e.status}")
            Log.e("", "Error Auth-statusCode: ${e.statusCode}")
            Log.e("", "Error Auth-cause: ${e.cause}")
        }
    }

    private fun handleDataAuth(account: GoogleSignInAccount) =
        firebaseAuth.signInWithCredential(
            GoogleAuthProvider.getCredential(
                account.idToken, null
            )
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val accountModel = UserModel(
                    idGoogle = account.id.toString(),
                    idToken = account.idToken.toString(),
                    email = account.email.toString(),
                    firstName = account.givenName.toString(),
                    lastName = account.familyName.toString(),
                    displayName = account.displayName.toString(),
                    photoUrl = account.photoUrl.toString(),
                )
                resultDataAuthSuccess.invoke(accountModel)
            } else {
                resultDataAuthError.invoke(
                    task.exception?.message ?: "Please check your connection"
                )
            }
        }

    private fun getGoogleSingInClient() = GoogleSignIn.getClient(
        context, GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()
    )

    fun isUserSignedIn() = GoogleSignIn.getLastSignedInAccount(context) != null

    fun signOut(
        isSuccess: () -> Unit,
        isError: (String?) -> Unit,
    ) {
        if (isUserSignedIn()) {
            getGoogleSingInClient().signOut().addOnCompleteListener {
                if (it.isSuccessful) {
                    isSuccess.invoke()
                } else {
                    isError.invoke(it.exception?.message)
                }
            }
        } else isError.invoke(null)
    }
}