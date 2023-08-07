package com.rznkolds.presentation.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.usecase.account.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignInUIState())
    val state: StateFlow<SignInUIState> = _state

    fun login(email: String, password: String) {

        val isValid = when {
            email.isEmpty() -> false
            password.isEmpty() -> false
            else -> true
        }

        if (isValid){

            signInUseCase(email, password).onEach { v->

                when (v) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(loading = "")
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = "")
                    }

                    is Resource.Success -> {

                        v.data.let {
                            _state.value = _state.value.copy(logged = it)
                        }
                    }
                }

            }.launchIn(viewModelScope)

        } else {
            _state.value = _state.value.copy(fail = "Boş alanları doldurunuz")
        }
    }
}