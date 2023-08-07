package com.rznkolds.domain.mapper.model.ui

import com.rznkolds.data.dto.Comment
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.CommentUI
import javax.inject.Inject

class CommentUIMapperImpl @Inject constructor() : BaseMapper<CommentUI, Comment> {

    override fun map(input: CommentUI): Comment {

        return Comment(
            uid = input.uid ?: "",
            name = input.name ?: "",
            profile = input.profile ?: "",
            coin = input.coin ?: "",
            comment = input.comment ?: "",
            date = input.date ?: 0,
            time = input.time ?: 0
        )
    }
}