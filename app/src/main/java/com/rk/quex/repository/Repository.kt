package com.rk.quex.repository

import com.rk.quex.api.RetrofitInstance
import com.rk.quex.model.CoinModel
import retrofit2.Response

class Repository {

    suspend fun getPost( key : String ) : Response<ArrayList<CoinModel>> {

        return RetrofitInstance.api.getPost ( key )
    }

}