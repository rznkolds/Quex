package com.rznkolds.data.repository.authenticator

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthenticatorImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val cloud: FirebaseStorage
) : Authenticator {

    override suspend fun getUid(): String = auth.currentUser?.uid.orEmpty()

    override suspend fun register(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun login(email: String, password: String): Boolean {

        var result = false

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            result = it.isSuccessful

        }.await()

        return result
    }

    override suspend fun setPicture(picture:Uri): String {

        var result = ""

        cloud.reference.child(getUid()).putFile(picture).addOnSuccessListener {

            it.metadata?.reference?.downloadUrl?.addOnSuccessListener { p ->

                result = p.toString()
            }

        }.await()

        return  result
    }

    override suspend fun getPicture(): Uri {

        return cloud.reference.child(auth.currentUser?.uid.toString()).downloadUrl.result
    }

    override suspend fun signOut() = auth.signOut()
}