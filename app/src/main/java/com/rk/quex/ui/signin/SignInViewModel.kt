package com.rk.quex.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rk.quex.data.repository.CoinRepo
import com.rk.quex.data.repository.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepo: UserRepo,
) : ViewModel() {

    private var _result = MutableLiveData<Boolean>()
    val result : LiveData<Boolean>
        get() = _result

    private var _fail = MutableLiveData<String>()
    val fail : LiveData<String>
        get() = _fail

    init {
        _result = userRepo.result
    }

    fun login(email: String, password: String) {

        val isValid = when {
            email.isEmpty() -> false
            password.isEmpty() -> false
            else -> true
        }

        if (isValid){
            userRepo.login(email, password)
        } else {
            _fail.value = "Boş alanları doldurunuz"
        }
    }
}