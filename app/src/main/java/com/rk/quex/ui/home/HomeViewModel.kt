package com.rk.quex.ui.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Coin
import com.rk.quex.data.repository.CoinRepo
import com.rk.quex.data.repository.MemberRepo

class HomeViewModel : ViewModel() {

    private var coinRepo = CoinRepo()
    private var memberRepo = MemberRepo()

    private var _coins = MutableLiveData<ArrayList<Coin>>()
    val coins : LiveData<ArrayList<Coin>>
        get() {
            return _coins
        }

    private var _picture = MutableLiveData<Uri>()
    val picture : LiveData<Uri>
        get() {
            return _picture
        }

    init {
        getCoins()

        getPicture()
    }

    private fun getCoins() {

        coinRepo.getCoins()

        _coins = coinRepo.coins
    }

    private fun getPicture(){

        memberRepo.getPicture()

        _picture = memberRepo.picture
    }
}