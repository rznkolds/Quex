package com.rk.quex.repository

import com.rk.quex.api.RetrofitInstance
import com.rk.quex.model.CoinModel
import retrofit2.Call

class Repository {

    suspend fun getPost( key : String ) : Call< List<CoinModel>> {

        return RetrofitInstance.api.getPost ( key )
    }
}