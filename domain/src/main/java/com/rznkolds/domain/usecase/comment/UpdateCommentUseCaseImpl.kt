package com.rznkolds.domain.usecase.comment

import com.rznkolds.data.dto.Comment
import com.rznkolds.data.dto.Status
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class UpdateCommentUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val statusMapper: BaseMapper<Status, StatusUI>,
    private val commentMapper: BaseMapper<CommentUI, Comment>,
) : UpdateCommentUseCase {

    override operator fun invoke(text: String, comment: CommentUI): Flow<Resource<StatusUI>> {

        return flow {

            try {
                emit(Resource.Loading)

                emit(
                    Resource.Success(
                        statusMapper.map(repository.updateComment(text, commentMapper.map(comment)))
                    )
                )

            } catch (e: IOException) {

                emit(Resource.Error("Couldn't reach server."))
            }

        }.flowOn(Dispatchers.IO)
    }
}