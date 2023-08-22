package com.kotlin.learn.core.network.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kotlin.learn.core.utilities.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseClient {

    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var databaseReference: DatabaseReference

    internal suspend fun <Z : Any> storeRequestToFirebase(
        data: Z,
        firestoreTable: String,
        onSuccess: (String) -> Unit,
        onError: () -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            databaseReference = firebaseDatabase.getReference(firestoreTable)
            val idFirestore = databaseReference.push().key.toString()
            databaseReference.child(idFirestore).setValue(data)
                .addOnSuccessListener {
                    onSuccess.invoke(idFirestore)
                }
                .addOnFailureListener {
                    onError.invoke()
                }
        }
    }

    internal suspend fun <Z : Any> fetchRequestFromFirebase(
        id: String,
        resources: Z,
        firestoreTable: String,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            firebaseDatabase.getReference(firestoreTable).child(id).addValueEventListener(
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