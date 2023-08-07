package com.rznkolds.domain.usecase.notification

import com.rznkolds.data.dto.Notification
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.list.BaseListMapper
import com.rznkolds.domain.model.NotificationUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetNotificationsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: BaseListMapper<Notification, NotificationUI>
) : GetNotificationsUseCase {

    override operator fun invoke(): Flow<Resource<List<NotificationUI>>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repository.getNotifications())))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}