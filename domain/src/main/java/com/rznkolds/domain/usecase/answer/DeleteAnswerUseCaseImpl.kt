package com.rznkolds.domain.usecase.answer

import com.rznkolds.data.dto.Answer
import com.rznkolds.data.dto.Comment
import com.rznkolds.data.dto.Status
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class DeleteAnswerUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val answerMapper: BaseMapper<AnswerUI, Answer>,
    private val statusMapper: BaseMapper<Status, StatusUI>
) : DeleteAnswerUseCase {

    override operator fun invoke(answer: AnswerUI): Flow<Resource<StatusUI>> = flow {

        try {
            emit(Resource.Loading)

            emit(
                Resource.Success(
                    statusMapper.map(repository.deleteAnswer(answerMapper.map(answer)))
                )
            )

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}