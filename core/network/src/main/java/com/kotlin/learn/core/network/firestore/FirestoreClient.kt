package com.kotlin.learn.core.network.firestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.Constant
import java.util.Objects

class FirestoreClient {

    private val firestore = Firebase.firestore

    private val TAG = this::class.java.simpleName

    internal fun <Z : Any> storeRequestToFirestore(
        id: String,
        data: Z,
        collection: String,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        onLoad.invoke()
        firestore.collection(collection)
            .document(id)
            .set(data)
            .addOnSuccessListener {
                onSuccess.invoke(id)
            }.addOnFailureListener {
                onError.invoke(it.message.toString())
            }
    }

    internal fun updateRequestToFirestore(
        id: String,
        data: Map<String, String>,
        collection: String,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit,
    ) {
        onLoad.invoke()
        firestore.collection(collection)
            .document(id)
            .update(data)
            .addOnSuccessListener {
                onSuccess.invoke(id)
            }.addOnFailureListener {
                onError.invoke(it.message.toString())
            }
    }

    internal fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        collection: String,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) {
        onLoad.invoke()
        firestore
            .collection(collection)
            .whereEqualTo("email", filter["email"])
            .limit(1)
            .get()
            .addOnSuccessListener { documents ->
                try {
                    if (documents != null) {
                        if (!documents.isEmpty) {
                            for (document in documents) {
                                try {
                                    val finalResult = document.toObject(UserModel::class.java)
                                    onSuccess.invoke(finalResult)
                                } catch (ex: Exception) {
                                    onError.invoke(ex.message.toString())
                                }
                            }
                        } else onError.invoke(Constant.DATA_NOT_FOUND)
                    } else {
                        onError.invoke(Constant.DATA_NOT_FOUND)
                    }
                } catch (ex: Exception) {
                    onError.invoke("Error writing document, ${ex.message.toString()}")
                }
            }.addOnFailureListener { ex ->
                onError.invoke("Failure writing document, ${ex.message.toString()}")
            }
    }

}