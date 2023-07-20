package com.kotlin.learn.core.network.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kotlin.learn.core.utilities.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseClient {

    private val table = "authorization"

    private var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = firebaseDatabase.getReference(table)

    internal inline fun <reified Z : Any> postFirebaseRequest(
        data: Z
    ) {
        val id = databaseReference.push().key.toString()
        databaseReference.child(id).child(table).setValue(data)
    }

    internal suspend fun <Z : Any> getFirebaseRequest(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            databaseReference.child(id).child(table).addValueEventListener(object : ValueEventListener {
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

//    val auth = dataSnapshot.getValue(AuthGoogleSignInModel::class.java) ?: return
//    Log.d("this", "Value auth is: $auth")

}