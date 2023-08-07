package com.rznkolds.domain.usecase.comment

import com.rznkolds.data.dto.Comment
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.list.BaseListMapper
import com.rznkolds.domain.model.CommentUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetCommentsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: BaseListMapper<Comment, CommentUI>
) : GetCommentsUseCase {

    override operator fun invoke(coin:String): Flow<Resource<List<CommentUI>>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repository.getComments(coin))))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}