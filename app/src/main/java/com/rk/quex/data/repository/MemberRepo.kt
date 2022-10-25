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
    private val service = Retrofit.userService()
    val result = MutableLiveData<Boolean>()
    val picture = MutableLiveData<Uri>()
    val user = MutableLiveData<User>()
    val favorites = MutableLiveData<ArrayList<Favorite>>()
    val calendar = Calendar.getInstance()
    val comments = MutableLiveData<ArrayList<Comment>>()
    val answers = MutableLiveData<ArrayList<Answer>>()

    // User login and registration processes

    fun login(email: String, password: String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            result.value = it.isSuccessful
        }
    }

    fun register(name: String, text: String, email: String, password: String, picture: Uri) {

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {

            cloud.child(auth.currentUser?.uid.toString()).putFile(picture).addOnSuccessListener {

                it.metadata?.reference?.downloadUrl?.addOnSuccessListener { p ->

                    val user = User(auth.currentUser?.uid.toString(), name, text, p.toString())

                    service.postUser(user).enqueue(object : Callback<String> {

                        override fun onResponse(call: Call<String>, response: Response<String>) {

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

        service.getUser(uid).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.isSuccessful) {

                    user.value = response.body()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {}
        })
    }

    // Get current user's favorite coin list

    fun getFavorites(uid: String) {

        service.getFavoriteCoins(uid)
            .enqueue(object : Callback<ArrayList<Favorite>> {

                override fun onResponse(
                    call: Call<ArrayList<Favorite>>, response: Response<ArrayList<Favorite>>
                ) {

                    if (response.isSuccessful) {

                        favorites.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Favorite>>, t: Throwable) {}
            })
    }

    // Saving and receiving comments

    fun postComment(coin: String, text: String) {

        val comment = Comment(
            auth.uid.toString(),
            "",
            "",
            coin,
            text,
            date(),
            time()
        )

        service.postComment(comment).enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {

                result.value = response.isSuccessful
            }

            override fun onFailure(call: Call<String>, t: Throwable) {}
        })
    }

    private fun date(): Int {

        val date = calendar[Calendar.DAY_OF_MONTH].toString() +
                calendar[Calendar.MONTH].toString() +
                calendar[Calendar.YEAR].toString()

        return date.toInt()
    }

    private fun time(): Int {

        val time = calendar[Calendar.MILLISECOND].toString() +
                calendar[Calendar.MINUTE].toString() +
                calendar[Calendar.HOUR_OF_DAY].toString()

        return time.toInt()
    }

    fun getComments(coin: String) {

        service.getComments(coin).enqueue(object : Callback<ArrayList<Comment>> {

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

    fun postAnswer(uid: String, coin: String, text: String, date: Int, time: Int) {

        val answer = Answer(
            auth.uid.toString(),
            "",
            uid,
            "",
            coin,
            text,
            date,
            time
        )

        service.postAnswer(answer).enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {

                result.value = response.isSuccessful
            }

            override fun onFailure(call: Call<String>, t: Throwable) {}
        })
    }

    fun getAnswers(coin: String, uid: String, date: Int, time: Int) {

        service.getAnswers(coin, uid, date, time).enqueue(object : Callback<ArrayList<Answer>> {

                override fun onResponse(
                    call: Call<ArrayList<Answer>>,
                    response: Response<ArrayList<Answer>>
                ) {

                    if (response.isSuccessful) {

                        answers.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Answer>>, t: Throwable) {}
            })
    }
}