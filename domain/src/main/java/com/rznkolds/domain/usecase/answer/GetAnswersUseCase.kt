package com.rznkolds.domain.usecase.answer

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.AnswerUI
import kotlinx.coroutines.flow.Flow

interface GetAnswersUseCase {

    operator fun invoke(
        coin: String,
        uid: String,
        date: Int,
        time: Int
    ): Flow<Resource<List<AnswerUI>>>
}