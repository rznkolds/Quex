package com.rk.quex.data.remote

import com.rk.quex.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    // Get and post user informations

    @GET("user/info.php")
    fun getUser(@Query("uid") uid: String): Call<User>

    @POST("user/save.php")
    fun postUser(@Body user: User): Call<Status>

    // Get user favorite coins

    @GET("favorite/list.php")
    fun getFavoriteCoins(@Query("uid") uid: String): Call<ArrayList<Favorite>>

    // Get and delete user notifications

    @GET("notification/list.php")
    fun getNotifications(@Query("uid") uid: String): Call<ArrayList<Notification>>

    @HTTP(method = "DELETE", path = "notification/delete.php", hasBody = true)
    fun deleteNotifications(@Query("uid") uid: String): Call<Status>

    // Get, post and delete user comments

    @GET("comment/list.php")
    fun getComments(@Query("coin") coin: String): Call<ArrayList<Comment>>

    @POST("comment/save.php")
    fun postComment(@Body comment: Comment): Call<Status>

    @PUT("comment/update.php")
    fun putComment(@Body comment: Comment): Call<Status>

    @HTTP(method = "DELETE", path = "comment/delete.php", hasBody = true)
    fun deleteComment(@Body comment: Comment): Call<Status>

    // Get, post and delete user answers

    @GET("answer/list.php")
    fun getAnswers(
        @Query("coin") coin: String,
        @Query("top") aboveUid: String,
        @Query("date") date: Int,
        @Query("time") time: Int
    ): Call<ArrayList<Answer>>

    @POST("answer/save.php")
    fun postAnswer(@Body answer: Answer): Call<Status>

    @PUT("answer/update.php")
    fun putAnswer(@Body answer: Answer): Call<Status>

    @HTTP(method = "DELETE", path = "answer/delete.php", hasBody = true)
    fun deleteAnswer(@Body answer: Answer): Call<Status>
}