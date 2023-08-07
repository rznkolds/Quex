package com.rznkolds.domain.usecase.comment

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.flow.Flow

interface AddCommentUseCase {

    operator fun invoke(coin: String, comment: String): Flow<Resource<StatusUI>>
}