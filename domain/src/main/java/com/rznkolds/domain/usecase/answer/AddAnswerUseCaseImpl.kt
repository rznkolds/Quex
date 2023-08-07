package com.rznkolds.domain.usecase.answer

import com.rznkolds.data.dto.Status
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class AddAnswerUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: BaseMapper<Status, StatusUI>
) : AddAnswerUseCase {

    override operator fun invoke(
        uid: String,
        coin: String,
        text: String,
        date: Int,
        time: Int
    ): Flow<Resource<StatusUI>> = flow {

        try {
            emit(Resource.Loading)

            emit(
                Resource.Success(
                    mapper.map(
                        repository.postAnswer(
                            uid,
                            coin,
                            text,
                            date,
                            time
                        )
                    )
                )
            )

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}