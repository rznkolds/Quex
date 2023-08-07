package com.rznkolds.domain.usecase.answer

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.flow.Flow

interface UpdateAnswerUseCase {

    operator fun invoke(text: String, answer: AnswerUI): Flow<Resource<StatusUI>>
}