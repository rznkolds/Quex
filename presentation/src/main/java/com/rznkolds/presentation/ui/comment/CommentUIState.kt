package com.rznkolds.presentation.ui.comment

import com.rznkolds.domain.model.CommentUI

data class CommentUIState(
    val comments: List<CommentUI>? = null,
    val added: Boolean? = null,
    val deleted: Boolean? = null,
    val loading: String? = null,
    val error: String? = null,
)
