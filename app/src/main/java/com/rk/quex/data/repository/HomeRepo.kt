package com.rk.quex.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rk.quex.common.Retrofit
import com.rk.quex.data.model.Coin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepo {

    fun list (): MutableLiveData<ArrayList<Coin>> {

        val result = MutableLiveData<ArrayList<Coin>>()

        Retrofit.coin().list().enqueue(object : Callback<ArrayList<Coin>> {

            override fun onResponse(call: Call<ArrayList<Coin>> , response: Response<ArrayList<Coin>>) {

                if (response.isSuccessful) {

                    result.value = response.body()
                } else {

                    Log.d("hata1", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ArrayList<Coin>> , t: Throwable) {

                Log.d("hata2",t.toString())
            }

        })

        return result
    }
}