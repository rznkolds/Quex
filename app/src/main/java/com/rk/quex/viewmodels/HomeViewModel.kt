package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Coin
import com.rk.quex.data.repository.HomeRepo

class HomeViewModel : ViewModel() {

    private var homeRepo = HomeRepo()

    fun coins(): MutableLiveData<ArrayList<Coin>> {

        return homeRepo.list()
    }
}