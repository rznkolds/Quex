package com.rk.quex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Coin
import com.rk.quex.data.repository.CoinRepo

class HomeViewModel : ViewModel() {

    private var coinRepo = CoinRepo()

    private var _coins = MutableLiveData<ArrayList<Coin>>()
    val coins : LiveData<ArrayList<Coin>>
        get() {

            return _coins
        }

    init {
        getCoins()
    }

    private fun getCoins() {

        coinRepo.getCoins()

        _coins = coinRepo.coins
    }
}