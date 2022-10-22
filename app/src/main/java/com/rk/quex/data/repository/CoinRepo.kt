package com.rk.quex.data.repository

import androidx.lifecycle.MutableLiveData
import com.rk.quex.common.Retrofit
import com.rk.quex.data.model.Coin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRepo {

    val coins = MutableLiveData<ArrayList<Coin>>()

    fun getCoins () {

        Retrofit.coinService().list().enqueue(object : Callback<ArrayList<Coin>> {

            override fun onResponse(call: Call<ArrayList<Coin>> , response: Response<ArrayList<Coin>>) {

                if (response.isSuccessful) {

                    coins.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Coin>> , t: Throwable) {}
        })
    }
}