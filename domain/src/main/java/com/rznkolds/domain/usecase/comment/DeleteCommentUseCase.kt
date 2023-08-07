package com.rznkolds.domain.usecase.comment

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.flow.Flow

interface DeleteCommentUseCase {

    operator fun invoke(comment: CommentUI): Flow<Resource<StatusUI>>
}