package com.rk.quex.api

import com.rk.quex.model.CoinModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTerminal {

    @GET("currencies/ticker")
    suspend fun getPost ( @Query("key") key : String ): Call<List<CoinModel>>
}