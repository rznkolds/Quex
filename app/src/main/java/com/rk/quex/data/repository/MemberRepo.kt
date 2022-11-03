package com.rk.quex.data.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rk.quex.common.Retrofit
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MemberRepo {

    private val auth by lazy { Firebase.auth }
    private val cloud by lazy { Firebase.storage.reference }
    private val userService = Retrofit.userService()
    val result = MutableLiveData<Boolean>()
    val picture = MutableLiveData<Uri>()
    val user = MutableLiveData<User>()

    // User login and registration processes

    fun login(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            result.value = it.isSuccessful
        }
    }

    fun register(name: String, description: String, email: String, password: String, picture: Uri) {

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

            cloud.child(auth.currentUser?.uid.toString()).putFile(picture).addOnSuccessListener {

                it.metadata?.reference?.downloadUrl?.addOnSuccessListener { p ->

                    val user = User(auth.currentUser?.uid.toString(), name, description, p.toString())

                    userService.postUser(user).enqueue(object : Callback<String> {

                        override fun onResponse(
                            call: Call<String>,
                            response: Response<String>
                        ) {
                            result.value = response.isSuccessful
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {}
                    })
                }
            }
        }
    }

    // Get current user's picture

    fun getPicture() {

        cloud.child(auth.currentUser?.uid.toString()).downloadUrl.addOnSuccessListener {
            picture.value = it
        }
    }

    // Get current user's data

    fun getProfile(uid: String) {

        userService.getUser(uid).enqueue(object : Callback<User> {

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    user.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {}
        })
    }
}