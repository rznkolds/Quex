package com.rznkolds.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.usecase.coin.GetCoinsUseCase
import com.rznkolds.domain.usecase.comment.DeleteCommentUseCase
import com.rznkolds.domain.usecase.user.GetUserPictureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val getUserPictureUseCase: GetUserPictureUseCase
) : ViewModel(){

    private val _state = MutableStateFlow(HomeUIState())
    val state: StateFlow<HomeUIState> = _state

    init {
        getCoins()
        getPicture()
    }

    private fun getCoins() {

        getCoinsUseCase().onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(coins = it)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getPicture() {

        getUserPictureUseCase().onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(picture = it)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }
}