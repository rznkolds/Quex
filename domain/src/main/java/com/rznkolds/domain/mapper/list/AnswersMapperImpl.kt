package com.rznkolds.domain.mapper.list

import com.rznkolds.data.dto.Answer
import com.rznkolds.domain.model.AnswerUI
import javax.inject.Inject

class AnswersMapperImpl @Inject constructor() : BaseListMapper<Answer, AnswerUI> {

    override fun map(input: List<Answer>): List<AnswerUI> {
        return input.map {
            AnswerUI(
                uid = it.uid ?: "",
                name = it.name ?: "",
                top = it.top ?: "",
                profile = it.profile ?: "",
                coin = it.coin ?: "",
                comment = it.comment ?: "",
                date = it.date ?: 0,
                time = it.time ?: 0
            )
        } ?: emptyList()
    }
}