package com.rk.quex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MemberRepo

class EnterViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    var result = MutableLiveData<Boolean>()

    fun userLogin(email: String, password: String) {

        memberRepo.login(email, password)

        result = memberRepo.result
    }
}