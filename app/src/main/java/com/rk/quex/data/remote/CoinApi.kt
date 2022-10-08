package com.rk.quex.data.remote

import com.rk.quex.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface CoinApi {

    @GET("currencies/ticker")
    fun postUser(@Body user: User): Call<String>
}