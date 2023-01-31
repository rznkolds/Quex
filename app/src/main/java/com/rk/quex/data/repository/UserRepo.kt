package com.rk.quex.data.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rk.quex.data.model.Status
import com.rk.quex.data.model.User
import com.rk.quex.data.remote.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(private val userService: UserService) {

    private val auth by lazy { Firebase.auth }
    private val cloud by lazy { Firebase.storage.reference }
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

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->

            if (it.isSuccessful){

                cloud.child(auth.currentUser?.uid.toString()).putFile(picture).addOnSuccessListener {

                    it.metadata?.reference?.downloadUrl?.addOnSuccessListener { p ->

                        val user = User(auth.currentUser?.uid.toString(), name, description, p.toString())

                        userService.postUser(user).enqueue(object : Callback<Status> {

                            override fun onResponse(
                                call: Call<Status>,
                                response: Response<Status>
                            ) {
                                result.value = response.body()?.received
                            }

                            override fun onFailure(call: Call<Status>, t: Throwable) {}
                        })
                    }
                }
            } else {
                result.value = false
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

    fun outProfile(){
        auth.signOut()
    }
}