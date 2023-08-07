package com.rznkolds.presentation.ui.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.usecase.notification.DeleteNotificationUseCase
import com.rznkolds.domain.usecase.notification.GetNotificationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NotificationUIState())
    val state: StateFlow<NotificationUIState> = _state

    init {
        getNotifications()
    }

    fun getNotifications() {

        getNotificationsUseCase().onEach { v ->

            when (v) {

                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(notifications = it)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun  deleteNotifications() {

        deleteNotificationUseCase().onEach { v ->

            when (v) {
                is Resource.Loading -> {
                    _state.value = _state.value.copy(loading = "")
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(error = "")
                }

                is Resource.Success -> {

                    v.data.let {
                        _state.value = _state.value.copy(deleted = it?.received)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }
}