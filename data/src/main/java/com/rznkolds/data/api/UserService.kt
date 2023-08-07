package com.rznkolds.data.api

import com.rznkolds.data.dto.*
import retrofit2.http.*

interface UserService {

    @GET("user/info.php")
    fun getUser(@Query("uid") uid: String): User

    @POST("user/save.php")
    fun postUser(@Body user: User): Status

    @GET("favorite/list.php")
    fun getFavoriteCoins(@Query("uid") uid: String): ArrayList<Favorite>

    @GET("notification/list.php")
    fun getNotifications(@Query("uid") uid: String): ArrayList<Notification>

    @HTTP(method = "DELETE", path = "notification/delete.php", hasBody = true)
    fun deleteNotifications(@Query("uid") uid: String): Status

    @GET("comment/list.php")
    fun getComments(@Query("coin") coin: String): ArrayList<Comment>

    @POST("comment/save.php")
    fun postComment(@Body comment: Comment): Status

    @PUT("comment/update.php")
    fun updateComment(@Body comment: Comment): Status

    @HTTP(method = "DELETE", path = "comment/delete.php", hasBody = true)
    fun deleteComment(@Body comment: Comment): Status

    @GET("answer/list.php")
    fun getAnswers(
        @Query("coin") coin: String,
        @Query("top") aboveUid: String,
        @Query("date") date: Int,
        @Query("time") time: Int
    ): ArrayList<Answer>

    @POST("answer/save.php")
    fun postAnswer(@Body answer: Answer): Status

    @PUT("answer/update.php")
    fun updateAnswer(@Body answer: Answer): Status

    @HTTP(method = "DELETE", path = "answer/delete.php", hasBody = true)
    fun deleteAnswer(@Body answer: Answer): Status
}