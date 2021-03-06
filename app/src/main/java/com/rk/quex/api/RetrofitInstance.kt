package com.rk.quex.api

import com.rk.quex.utils.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {

        Retrofit.Builder()
            .baseUrl ( BASE_URL )
            .addConverterFactory ( GsonConverterFactory.create() )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val api: ApiTerminal by lazy {

        retrofit.create ( ApiTerminal::class.java )
    }
}