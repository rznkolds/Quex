package com.rk.quex.data.remote

import com.rk.quex.data.model.Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {

    @GET("coins/markets?vs_currency=usd")
    fun list(): Call<ArrayList<Coin>>
}