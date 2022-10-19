package com.rk.quex.data.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
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


class MemberRepo {

    private val cloud by lazy { Firebase.storage.reference }
    val comments = MutableLiveData<ArrayList<Comment>>()
    val answers = MutableLiveData<ArrayList<Answer>>()
    private val auth by lazy { Firebase.auth }
    val result = MutableLiveData<Boolean>()

    // User login and registration processes

    fun login(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful) {

                result.value = true
            }
        }
    }

    fun register(name: String, text: String, email: String, password: String, picture: Uri) {

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

    // Get current user's data

    fun getUser(uid: String): MutableLiveData<User> {

        val result = MutableLiveData<User>()

        Retrofit.userService().getUser(uid).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.isSuccessful) {

                    result.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {}
        })

        return result
    }

    // Get current user's favorite coin list

    fun getFavoriteCoins(uid: String): MutableLiveData<ArrayList<Favorite>> {

        val result = MutableLiveData<ArrayList<Favorite>>()

        Retrofit.userService().getFavoriteCoins(uid)
            .enqueue(object : Callback<ArrayList<Favorite>> {

                override fun onResponse(
                    call: Call<ArrayList<Favorite>>, response: Response<ArrayList<Favorite>>
                ) {

                    if (response.isSuccessful) {

                        result.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Favorite>>, t: Throwable) {}
            })

        return result
    }

    // Saving and receiving comments

    fun postComment(coin: String, comment: String, date: Int, time: Int) {

        val comment = Comment(auth.uid.toString(), "", "", coin, comment, date, time)

        Retrofit.userService().postComment(comment).enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {

                result.value = response.isSuccessful
            }

            override fun onFailure(call: Call<String>, t: Throwable) {}
        })
    }

    fun getComments(coin: String) {

        Retrofit.userService().getComments(coin).enqueue(object : Callback<ArrayList<Comment>> {

            override fun onResponse(
                call: Call<ArrayList<Comment>>, response: Response<ArrayList<Comment>>
            ) {

                if (response.isSuccessful) {

                    comments.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {}
        })
    }

    // Saving and receiving answers

    fun postAnswer(above_uid: String, coin: String, comment: String, top_date: Int, top_time: Int) {

        val answer =
            Answer(auth.uid.toString(), "", above_uid, "", coin, comment, top_date, top_time)

        Retrofit.userService().postAnswer(answer).enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {

                result.value = response.isSuccessful
            }

            override fun onFailure(call: Call<String>, t: Throwable) {}
        })
    }

    fun getAnswers(coin: String, above_uid: String, top_date: Int, top_time: Int) {

        Retrofit.userService().getAnswers(coin, above_uid, top_date, top_time).enqueue(object : Callback<ArrayList<Answer>> {

                override fun onResponse(call: Call<ArrayList<Answer>>, response: Response<ArrayList<Answer>>) {

                    if (response.isSuccessful) {

                        answers.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Answer>>, t: Throwable) { }
        })
    }
}