package com.rznkolds.domain.usecase.answer

import com.rznkolds.data.dto.Answer
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.list.BaseListMapper
import com.rznkolds.domain.model.AnswerUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetAnswersUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: BaseListMapper<Answer, AnswerUI>
) : GetAnswersUseCase {

    override operator fun invoke(
        coin: String,
        uid: String,
        date: Int,
        time: Int
    ): Flow<Resource<List<AnswerUI>>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repository.getAnswers(coin, uid, date, time))))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}