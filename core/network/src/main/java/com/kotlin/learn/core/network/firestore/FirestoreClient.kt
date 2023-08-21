package com.kotlin.learn.core.network.firestore

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.TransactionUtil

class FirestoreClient {

    private val firestore = Firebase.firestore

    private val TAG = this::class.java.simpleName

    internal fun <Z : Any> storeRequestToFirestore(
        id: String,
        data: Z,
        firestoreTable: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        firestore.collection(firestoreTable)
            .document(id)
            .set(data)
            .addOnSuccessListener {
                onSuccess.invoke(id)
            }.addOnFailureListener {
                onError.invoke(it.message.toString())
            }
    }

    internal fun <Z : Any> fetchRequestToFirestore(
        id: String,
        firestoreTable: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) {
        firestore.collection(firestoreTable)
            .whereEqualTo("email", "boys.mtv@gmail.com")
            .whereEqualTo("password", "123456789")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    try {
                        val finalResult = document.toObject(resources::class.java)
                        onSuccess.invoke(finalResult)
                    } catch (ex: Exception) {
                        onError.invoke(ex.message.toString())
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Failure, Under maintenance, please try again later.", exception)
                onError.invoke("Failure, Under maintenance, please try again later.")
            }
            .addOnCompleteListener {
                Log.w(TAG, "Completed, Under maintenance, please try again later. ${it.exception?.message}")
                if (it.isSuccessful){
                    if (it.exception != null){
                        onError.invoke("Under maintenance, please try again later.")
                    }else{
                        onError.invoke("Invalid username or password")
                    }
                }else{
                    onError.invoke("Invalid username or password")
                }
            }
    }

}