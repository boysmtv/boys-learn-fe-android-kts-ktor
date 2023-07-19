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
import com.kotlin.learn.core.model.AuthGoogleSignInModel
import com.kotlin.learn.feature.auth.R

class GoogleSignInExt(
    private val resultDataAuth: (AuthGoogleSignInModel) -> Unit
) {
    lateinit var context: Context

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
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleDataAuth(account: GoogleSignInAccount) =
        firebaseAuth.signInWithCredential(
            GoogleAuthProvider.getCredential(
                account.idToken, null
            )
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val accountModel = AuthGoogleSignInModel(
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
                Log.e("TAG", "BOYS-handleDataAuth : $accountModel")
                resultDataAuth.invoke(accountModel)
            } else {
                Log.e("TAG", "BOYS-handleDataAuth : Error")
                resultDataAuth.invoke(AuthGoogleSignInModel())
            }
        }

    private fun getGoogleSingInClient() = GoogleSignIn.getClient(
        context, GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()
    )

    private fun isUserSignedIn() = GoogleSignIn.getLastSignedInAccount(context) != null

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