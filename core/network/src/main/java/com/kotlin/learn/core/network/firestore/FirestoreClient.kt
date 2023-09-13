package com.kotlin.learn.core.network.firestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kotlin.learn.core.common.util.network.ResultCallback
import com.kotlin.learn.core.model.UserModel
import com.kotlin.learn.core.utilities.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

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

    internal fun <T> updateRequestToFirestore(
        id: String,
        data: Map<String, T>,
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

    fun <T : Any> fetchDataFromFirestoreAsync(
        filter: Pair<String, String>,
        resources: T,
        collection: String,
    ): Flow<ResultCallback<T>> = callbackFlow {
        trySend(ResultCallback.Loading)
        try {
            val snapshot = firestore.collection(collection)
                .whereEqualTo(filter.first, filter.second)
                .limit(1)
                .get()
                .await()

            if (snapshot != null) {
                if (!snapshot.isEmpty) {
                    for (document in snapshot) {
                        try {
                            val finalResult = document.toObject(resources::class.java)
                            trySend(ResultCallback.Success(finalResult))
                        } catch (ex: Exception) {
                            trySend(ResultCallback.Error("Could not get data. " + ex.message.toString()))
                        }
                    }
                } else
                    trySend(ResultCallback.Error(Constant.DATA_NOT_FOUND))
            } else
                trySend(ResultCallback.Error(Constant.DATA_NOT_FOUND))

            close()
            awaitCancellation()
        } catch (e: Exception) {
            trySend(ResultCallback.Error(e.message ?: e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun <T : Any> updateRequestToFirestoreAsync(
        id: String,
        data: Map<String, T>,
        collection: String,
    ): Flow<ResultCallback<String>> = callbackFlow {
        trySend(ResultCallback.Loading)
        try {
            firestore.collection(collection)
                .document(id)
                .update(data)
                .addOnSuccessListener {
                    trySend(ResultCallback.Success(id))
                }.addOnFailureListener {
                    trySend(ResultCallback.Error("Failed update data " + it.message.toString()))
                }
                .await()
            close()
            awaitCancellation()
        } catch (e: Exception) {
            trySend(ResultCallback.Error(e.message ?: e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}