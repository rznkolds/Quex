package com.rznkolds.domain.usecase.notification

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.flow.Flow

interface DeleteNotificationUseCase {

    operator fun invoke(): Flow<Resource<StatusUI>>
}