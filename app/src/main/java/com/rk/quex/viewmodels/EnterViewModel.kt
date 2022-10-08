package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MembershipRepo

class EnterViewModel : ViewModel() {

    private var enterRepo = MembershipRepo()

    fun loginUser(email: String, password: String): MutableLiveData<Boolean> {

        return enterRepo.login(email, password)
    }
}