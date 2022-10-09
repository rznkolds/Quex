package com.rk.quex.common

import com.google.gson.GsonBuilder
import com.rk.quex.data.remote.CoinService
import com.rk.quex.data.remote.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {

    fun user(): UserService {

        val user: UserService by lazy { retrofit(Constants.BASE_USER_URL).create(UserService::class.java) }

        return user
    }

    fun coin(): CoinService {

        val coin: CoinService by lazy { retrofit(Constants.BASE_COIN_URL).create(CoinService::class.java) }

        return coin
    }

    private fun retrofit(url: String): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(url)
            .build()
    }
}