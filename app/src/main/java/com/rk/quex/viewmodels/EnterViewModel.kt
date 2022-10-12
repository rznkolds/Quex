package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MemberRepo

class EnterViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    fun loginUser(email: String, password: String): MutableLiveData<Boolean> {

        return memberRepo.login(email, password)
    }
}