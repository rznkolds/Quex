package com.rznkolds.presentation.ui.signup

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.usecase.account.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SignUpUIState())
    val state: StateFlow<SignUpUIState> = _state

    fun register(
        name: String,
        description: String,
        email: String,
        password: String,
        picture: Uri?
    ) {

        val isValid = when {
            name.isEmpty() -> false
            description.isEmpty() -> false
            email.isEmpty() -> false
            password.isEmpty() -> false
            else -> true
        }

        if (isValid && picture != null) {

            signUpUseCase(name, description, email, password, picture).onEach { v ->

                when (v) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(loading = "")
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(error = "")
                    }

                    is Resource.Success -> {

                        v.data.let {
                            _state.value = _state.value.copy(registered = it?.received)
                        }
                    }
                }

            }.launchIn(viewModelScope)

        } else if (picture == null) {

            _state.value = _state.value.copy(fail = "Bir resim seçin")
        } else {

            _state.value = _state.value.copy(fail = "Boş kısımları belirtin")
        }
    }
}