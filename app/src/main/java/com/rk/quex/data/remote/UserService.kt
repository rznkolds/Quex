package com.rk.quex.data.remote

import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment
import com.rk.quex.data.model.Favorite
import com.rk.quex.data.model.User
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface UserService {

    // Get and post user informations

    @GET("info")
    fun getUser(@Query("uid") uid: String): Call<User>

    @POST("save")
    fun postUser(@Body user: User): Call<String>

    // Get user favorite coins

    @GET("coin/list")
    fun getFavoriteCoins(@Query("uid") uid: String): Call<ArrayList<Favorite>>

    // Get, post and delete user comments

    @GET("comment/list")
    fun getComments(@Query("coin") coin: String): Call<ArrayList<Comment>>

    @POST("comment/save")
    fun postComment(@Body comment: Comment): Call<String>

    @HTTP(method = "DELETE", path = "comment/delete", hasBody = true)
    fun deleteComment(@Body comment: Comment): Call<String>

    // Get, post and delete user answers

    @GET("answer/list")
    fun getAnswers(
        @Query("coin") coin: String,
        @Query("above_uid") aboveUid: String,
        @Query("top_date") top_date: Int,
        @Query("top_time") top_time: Int
    ): Call<ArrayList<Answer>>

    @POST("answer/save")
    fun postAnswer(@Body answer: Answer): Call<String>

    @HTTP(method = "DELETE", path = "answer/delete", hasBody = true)
    fun deleteAnswer(@Body answer: Answer): Call<String>
}