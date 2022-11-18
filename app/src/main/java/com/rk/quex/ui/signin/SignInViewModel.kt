package com.rk.quex.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.MemberRepo

class SignInViewModel : ViewModel() {

    private var memberRepo = MemberRepo()

    private var _result = MutableLiveData<Boolean>()
    val result : LiveData<Boolean>
        get() = _result

    private var _fail = MutableLiveData<String>()
    val fail : LiveData<String>
        get() = _fail

    init {
        _result = memberRepo.result
    }

    fun login(email: String, password: String) {

        val isValid = when {
            email.isEmpty() -> false
            password.isEmpty() -> false
            else -> true
        }

        if (isValid){
            memberRepo.login(email, password)
        } else {
            _fail.value = "Boş alanları doldurunuz"
        }
    }
}