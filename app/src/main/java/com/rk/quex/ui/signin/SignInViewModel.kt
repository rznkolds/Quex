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

    private var _failMessage = MutableLiveData<String>()
    val failMessage : LiveData<String>
        get() = _failMessage

    fun login(email: String, password: String) {

        val isValid = when {
            email.isEmpty() -> false
            password.isEmpty() -> false
            else -> true
        }

        if (isValid){
            memberRepo.login(email, password)
            _result = memberRepo.result
        } else {
            _failMessage.value = "Boş alanları doldurunuz"
        }
    }
}