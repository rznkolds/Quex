package com.rznkolds.domain.usecase.answer

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.flow.Flow

interface DeleteAnswerUseCase {

    operator fun invoke(answer: AnswerUI): Flow<Resource<StatusUI>>
}