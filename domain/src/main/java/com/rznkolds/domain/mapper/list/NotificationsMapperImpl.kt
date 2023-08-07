package com.rznkolds.domain.mapper.list

import com.rznkolds.data.dto.Notification
import com.rznkolds.domain.model.NotificationUI
import javax.inject.Inject

class NotificationsMapperImpl @Inject constructor() : BaseListMapper<Notification, NotificationUI> {

    override fun map(input: List<Notification>): List<NotificationUI> {
        return input.map {
            NotificationUI(
                uid = it.uid ?: "",
                coin = it.coin ?: "",
                name = it.name ?: "",
                profile = it.profile ?: "",
                date = it.date ?: 0,
                time = it.time ?: 0,
            )
        } ?: emptyList()
    }
}