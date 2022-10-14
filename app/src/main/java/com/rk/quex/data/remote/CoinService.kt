package com.rk.quex.data.remote

import com.rk.quex.data.model.Coin
import retrofit2.Call
import retrofit2.http.GET

interface CoinService {

    @GET("coins/markets?vs_currency=usd")
    fun list(): Call<ArrayList<Coin>>
}