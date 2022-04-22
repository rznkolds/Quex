package com.rk.quex.api

import com.rk.quex.model.CoinModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiTerminal {

    @GET("currencies/ticker?key={key}")
    suspend fun getPost ( @Path("key") key : String ): Response<ArrayList<CoinModel>>
}