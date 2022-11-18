package com.rk.quex.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rk.quex.data.model.Notification
import com.rk.quex.data.repository.CoinRepo

class NotificationViewModel : ViewModel() {

    private val coinRepo = CoinRepo()

    private var _notifications = MutableLiveData<ArrayList<Notification>>()
    val notifications: LiveData<ArrayList<Notification>>
        get() = _notifications

    private var _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    init {
        getNotifications()

        _notifications = coinRepo.notifications
        _result = coinRepo.result
    }

    fun getNotifications() = coinRepo.getNotifications()

    fun  deleteNotifications() = coinRepo.deleteNotifications()
}