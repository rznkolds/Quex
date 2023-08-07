package com.rznkolds.domain.mapper.list

import com.rznkolds.data.dto.Comment
import com.rznkolds.domain.model.CommentUI
import javax.inject.Inject

class CommentsMapperImpl @Inject constructor() : BaseListMapper<Comment, CommentUI> {

    override fun map(input: List<Comment>): List<CommentUI> {
        return input.map {
            CommentUI(
                uid = it.uid ?: "",
                name = it.name ?: "",
                profile = it.profile ?: "",
                coin = it.coin ?: "",
                comment = it.comment ?: "",
                date = it.date ?: 0,
                time = it.time ?: 0,
            )
        } ?: emptyList()
    }
}