package com.rk.quex

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.quex.model.CoinModel
import com.rk.quex.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel ( private val repository: Repository ) : ViewModel() {

    val response = MutableLiveData<Response<ArrayList<CoinModel>>>()

    fun getPost ( key : String ) {

        viewModelScope.launch {

            response.value = repository.getPost ( key )
        }
    }

}