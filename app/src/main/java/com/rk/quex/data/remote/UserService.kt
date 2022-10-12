package com.rk.quex.data.remote

import com.rk.quex.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @POST("save")
    fun postUser(@Body user: User): Call<String>

    @GET("info")
    fun userInfo(@Query("uid") uid: String): Call<User>
}