package com.rznkolds.presentation.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.usecase.favorite.GetFavoritesUseCase
import com.rznkolds.domain.usecase.user.GetUserUseCase
import com.rznkolds.domain.usecase.account.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileUIState())
    val state: StateFlow<ProfileUIState> = _state

    init {
        savedStateHandle.get<String>("uid")?.let {
            getProfile(it)
            getFavorites(it)
        }
    }

    fun outProfile() {

        viewModelScope.launch { signOutUseCase() }
    }

    private fun getProfile(uid: String) {

        getUserUseCase(uid).onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(user = it)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getFavorites(uid: String) {

        getFavoritesUseCase(uid).onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(favorites = it)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }
}