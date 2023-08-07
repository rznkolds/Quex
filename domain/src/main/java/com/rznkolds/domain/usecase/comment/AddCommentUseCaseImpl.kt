package com.rznkolds.domain.usecase.comment

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

class AddCommentUseCaseImpl @Inject constructor(
    private val repo: CoinRepository,
    private val mapper: BaseMapper<Status, StatusUI>
): AddCommentUseCase {

    override operator fun invoke(coin: String, comment: String): Flow<Resource<StatusUI>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repo.postComment(coin,comment))))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}