package com.rk.quex.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.data.model.*
import com.rk.quex.data.remote.CoinService
import com.rk.quex.data.remote.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class CoinRepo @Inject constructor(private val userService: UserService, private val coinService: CoinService) {

    val result = MutableLiveData<Boolean>()
    val coins = MutableLiveData<ArrayList<Coin>>()
    val favorites = MutableLiveData<ArrayList<Favorite>>()
    val notifications = MutableLiveData<ArrayList<Notification>>()
    val comments = MutableLiveData<ArrayList<Comment>>()
    val answers = MutableLiveData<ArrayList<Answer>>()

    private val auth by lazy { Firebase.auth }
    private val calendar by lazy { Calendar.getInstance() }

    fun getCoins() {

        coinService.getCoins().enqueue(object : Callback<ArrayList<Coin>> {

            override fun onResponse(
                call: Call<ArrayList<Coin>>,
                response: Response<ArrayList<Coin>>
            ) {
                if (response.isSuccessful) {
                    coins.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Coin>>, t: Throwable) {}
        })
    }

    // Get current user's favorite coin list

    fun getFavorites(uid: String) {

        userService.getFavoriteCoins(uid).enqueue(object : Callback<ArrayList<Favorite>> {

            override fun onResponse(
                call: Call<ArrayList<Favorite>>,
                response: Response<ArrayList<Favorite>>
            ) {
                if (response.isSuccessful) {
                    favorites.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Favorite>>, t: Throwable) {}
        })
    }

    // Get and delete current user's notifications

    fun getNotifications() {

        userService.getNotifications(auth.uid.toString())
            .enqueue(object : Callback<ArrayList<Notification>> {

                override fun onResponse(
                    call: Call<ArrayList<Notification>>,
                    response: Response<ArrayList<Notification>>
                ) {
                    if (response.isSuccessful) {
                        notifications.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<Notification>>, t: Throwable) {}
            })
    }

    fun deleteNotifications() {

        userService.deleteNotifications(auth.uid.toString()).enqueue(object : Callback<Status> {

            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                result.value = response.body()?.received
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {}
        })
    }

    // Saving and receiving comments

    fun getComments(coin: String) {

        userService.getComments(coin).enqueue(object : Callback<ArrayList<Comment>> {

            override fun onResponse(
                call: Call<ArrayList<Comment>>,
                response: Response<ArrayList<Comment>>
            ) {
                if (response.isSuccessful) {
                    comments.value = response.body()
                    Log.d("1 :", response.body().toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {}
        })
    }

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

        userService.postComment(comment).enqueue(object : Callback<Status> {

            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                result.value = response.body()?.received
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {}
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

    fun putComment(text: String, cmt: Comment) {

        val comment = Comment(
            cmt.uid,
            "",
            "",
            cmt.coin,
            text,
            cmt.date,
            cmt.time
        )

        userService.putComment(comment).enqueue(object : Callback<Status> {

            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                result.value = response.body()?.received
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {}
        })
    }

    fun deleteComment(comment: Comment) {

        userService.deleteComment(comment).enqueue(object : Callback<Status> {

            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                result.value = response.body()?.received
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {}
        })
    }

    // Saving and receiving answers

    fun getAnswers(coin: String, uid: String, date: Int, time: Int) {

        userService.getAnswers(coin, uid, date, time).enqueue(object : Callback<ArrayList<Answer>> {

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

        userService.postAnswer(answer).enqueue(object : Callback<Status> {

            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                result.value = response.body()?.received
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {}
        })
    }

    fun putAnswer(text: String, ans: Answer) {

        val answer = Answer(
            ans.uid,
            "",
            ans.top,
            "",
            ans.coin,
            text,
            ans.date,
            ans.time
        )

        userService.putAnswer(answer).enqueue(object : Callback<Status> {

            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                result.value = response.body()?.received
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {}
        })
    }

    fun deleteAnswer(answer: Answer) {

        userService.deleteAnswer(answer).enqueue(object : Callback<Status> {

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