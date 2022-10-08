package com.rk.quex.common

import com.google.gson.GsonBuilder
import com.rk.quex.data.remote.CoinApi
import com.rk.quex.data.remote.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {

    fun userApi(): UserApi {

        val user: UserApi by lazy { retrofit(Constants.BASE_USER_URL).create(UserApi::class.java) }

        return user
    }

    fun coinApi(): CoinApi {

        val coin: CoinApi by lazy { retrofit(Constants.BASE_COIN_URL).create(CoinApi::class.java) }

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