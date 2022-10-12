package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Coin
import com.rk.quex.data.repository.CoinRepo

class HomeViewModel : ViewModel() {

    private var coinRepo = CoinRepo()

    fun coins(): MutableLiveData<ArrayList<Coin>> {

        return coinRepo.list()
    }
}