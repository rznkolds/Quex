package com.rznkolds.domain.usecase.notification

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.NotificationUI
import kotlinx.coroutines.flow.Flow

interface GetNotificationsUseCase {

    operator fun invoke(): Flow<Resource<List<NotificationUI>>>
}