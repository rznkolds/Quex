package com.rk.quex.ui.enter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MemberRepo

class EnterViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _result = MutableLiveData<Boolean>()
    val result : LiveData<Boolean>
        get() {
            return _result
        }


    fun login(email: String, password: String) {

        memberRepo.login(email, password)

        _result = memberRepo.result
    }
}