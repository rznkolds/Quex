package com.rk.quex.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EnterRepo {

    private val auth by lazy{Firebase.auth}

    fun login(email: String, password: String): MutableLiveData<Boolean> {

        val result = MutableLiveData<Boolean>()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful) {

                result.value = true
            }
        }

        return result
    }
}