package com.rznkolds.presentation.ui.answer

import com.rznkolds.domain.model.AnswerUI

data class AnswerUIState(
    val answers: List<AnswerUI>? = null,
    val added: Boolean? = null,
    val deleted: Boolean? = null,
    val loading: String? = null,
    val error: String? = null,
)
