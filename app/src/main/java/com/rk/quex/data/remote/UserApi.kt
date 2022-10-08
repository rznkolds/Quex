package com.rk.quex.data.remote

import com.rk.quex.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("save")
    fun postUser(@Body user: User): Call<String>
}