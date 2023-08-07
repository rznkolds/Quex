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
import com.rznkolds.domain.usecase.comment.UpdateCommentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class UpdateAnswerUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val statusMapper: BaseMapper<Status, StatusUI>,
    private val answerMapper: BaseMapper<AnswerUI, Answer>,
) : UpdateAnswerUseCase {

    override operator fun invoke(text: String, answer: AnswerUI): Flow<Resource<StatusUI>> {

        return flow {

            try {
                emit(Resource.Loading)

                emit(
                    Resource.Success(
                        statusMapper.map(repository.updateAnswer(text, answerMapper.map(answer)))
                    )
                )

            } catch (e: IOException) {

                emit(Resource.Error("Couldn't reach server."))
            }

        }.flowOn(Dispatchers.IO)
    }
}