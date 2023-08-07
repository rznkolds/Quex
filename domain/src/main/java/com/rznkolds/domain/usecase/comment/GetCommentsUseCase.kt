package com.rznkolds.domain.usecase.comment

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.CoinUI
import com.rznkolds.domain.model.CommentUI
import kotlinx.coroutines.flow.Flow

interface GetCommentsUseCase {

    operator fun invoke(coin:String): Flow<Resource<List<CommentUI>>>
}