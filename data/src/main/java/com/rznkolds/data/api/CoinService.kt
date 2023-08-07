package com.rznkolds.data.api

import com.rznkolds.data.dto.Coin
import retrofit2.Response
import retrofit2.http.GET

interface CoinService {

    @GET("coins/markets?vs_currency=usd")
    fun getCoins(): ArrayList<Coin>
}