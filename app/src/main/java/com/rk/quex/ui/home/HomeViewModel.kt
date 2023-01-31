package com.rk.quex.ui.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Coin
import com.rk.quex.data.repository.CoinRepo
import com.rk.quex.data.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coinRepo: CoinRepo,
    private val userRepo: UserRepo
) : ViewModel(){

    private var _coins = MutableLiveData<ArrayList<Coin>>()
    val coins: LiveData<ArrayList<Coin>>
        get() = _coins

    private var _picture = MutableLiveData<Uri>()
    val picture: LiveData<Uri>
        get() = _picture

    init {
        getCoins()
        getPicture()

        _coins = coinRepo.coins
        _picture = userRepo.picture
    }

    private fun getCoins() = coinRepo.getCoins()

    private fun getPicture() = userRepo.getPicture()
}