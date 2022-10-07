package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.repository.EnterRepo

class EnterViewModel : ViewModel() {

    private var enterRepo = EnterRepo()

    fun loginUser(email: String, password: String): MutableLiveData<Boolean> {

        return enterRepo.login(email, password)
    }
}