package com.rznkolds.domain.usecase.answer

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.model.NotificationUI
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.flow.Flow

interface AddAnswerUseCase {

    operator fun invoke(
        uid: String,
        coin: String,
        text: String,
        date: Int,
        time: Int
    ): Flow<Resource<StatusUI>>
}