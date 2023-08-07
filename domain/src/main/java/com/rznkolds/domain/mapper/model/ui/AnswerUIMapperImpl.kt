package com.rznkolds.domain.mapper.model.ui

import com.rznkolds.data.dto.Answer
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.AnswerUI
import javax.inject.Inject

class AnswerUIMapperImpl @Inject constructor() : BaseMapper<AnswerUI, Answer> {

    override fun map(input: AnswerUI): Answer {
        return Answer(
            uid = input.uid ?: "",
            name = input.name ?: "",
            top = input.top ?: "",
            profile = input.profile ?: "",
            coin = input.coin ?: "",
            comment = input.comment ?: "",
            date = input.date ?: 0,
            time = input.time ?: 0
        )
    }
}