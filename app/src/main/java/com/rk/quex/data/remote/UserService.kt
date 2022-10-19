package com.rk.quex.data.remote

import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import com.rk.quex.pieces.Answers
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {

    // Get and post user informations

    @GET("info")
    fun getUser(@Query("uid") uid: String): Call<User>

    @POST("save")
    fun postUser(@Body user: User): Call<String>

    // Get user favorite coins

    @GET("coin/list")
    fun getFavoriteCoins(@Query("uid") uid: String): Call<ArrayList<Favorite>>

    // Get and post user comment

    @GET("comment/list")
    fun getComments(@Query("coin") coin: String): Call<ArrayList<Comment>>

    @POST("comment/save")
    fun postComment(@Body comment: Comment): Call<String>

    // Get and post user answers

    @GET("answer/list")
    fun getAnswers(
        @Query("coin") coin: String,
        @Query("above_uid") above_uid: String,
        @Query("top_date") top_date: Int,
        @Query("top_time") top_time: Int
    ): Call<ArrayList<Answer>>

    @POST("answer/save")
    fun postAnswer(@Body answer: Answer): Call<String>
}