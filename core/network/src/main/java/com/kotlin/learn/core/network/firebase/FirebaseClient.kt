package com.kotlin.learn.core.network.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kotlin.learn.core.utilities.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseClient {

    private val authTable = "authorization"

    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.getReference(authTable)

    internal suspend fun <Z : Any> storeRequestToFirestore(
        data: Z,
        onSuccess: (String) -> Unit,
        onError: () -> Unit,
    ) =
        withContext(Dispatchers.IO) {
            val idFirestore = databaseReference.push().key.toString()
            databaseReference.child(idFirestore).setValue(data)
                .addOnSuccessListener {
                    onSuccess.invoke(idFirestore)
                }
                .addOnFailureListener {
                    onError.invoke()
                }
        }

    internal suspend fun <Z : Any> fetchRequestFromFirestore(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            databaseReference.child(id).addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val data = dataSnapshot.getValue(resources::class.java)
                        if (data != null) onSuccess.invoke(data)
                        else onError.invoke(Constant.DATA_NOT_FOUND)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        onError.invoke(error.message)
                    }
                })
        }
    }

    internal suspend fun <Z : Any> getFirebaseRequests(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Z? {
        return null
    }
}