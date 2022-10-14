package com.rk.quex.data.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rk.quex.common.Retrofit
import com.rk.quex.data.model.Comment
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemberRepo {

    private val cloud by lazy { Firebase.storage.reference }
    private val auth by lazy { Firebase.auth }
    val result = MutableLiveData<Boolean>()

    fun userLogin(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful) {

                result.value = true
            }
        }
    }

    fun userRegister(name: String, text: String, email: String, password: String, picture: Uri) {

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

            cloud.child(auth.currentUser?.uid.toString()).putFile(picture).addOnSuccessListener {

                it.metadata?.reference?.downloadUrl?.addOnSuccessListener { p ->

                    val user = User(auth.currentUser?.uid.toString(), name, text, p.toString())

                    Retrofit.userService().postUser(user).enqueue(object : Callback<String> {

                        override fun onResponse(call: Call<String>, response: Response<String>) {

                            if (response.isSuccessful) {

                                result.value = true
                            }
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {}
                    })
                }
            }
        }
    }

    fun getProfileInfo(uid: String): MutableLiveData<User> {

        val result = MutableLiveData<User>()

        Retrofit.userService().userInformations(uid).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.isSuccessful) {

                    result.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {}
        })

        return result
    }

    fun getFavoriteList(uid: String): MutableLiveData<ArrayList<Favorite>> {

        val result = MutableLiveData<ArrayList<Favorite>>()

        Retrofit.userService().favoriteCoins(uid).enqueue(object : Callback<ArrayList<Favorite>> {

            override fun onResponse(
                call: Call<ArrayList<Favorite>>,
                response: Response<ArrayList<Favorite>>
            ) {

                if (response.isSuccessful) {

                    result.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Favorite>>, t: Throwable) {}
        })

        return result
    }

    fun getCommentList(coin: String): MutableLiveData<ArrayList<Comment>> {

        val result = MutableLiveData<ArrayList<Comment>>()

        Retrofit.userService().coinComments(coin).enqueue(object : Callback<ArrayList<Comment>> {

            override fun onResponse(
                call: Call<ArrayList<Comment>>,
                response: Response<ArrayList<Comment>>
            ) {

                if (response.isSuccessful) {

                    result.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {}
        })

        return result
    }
}