package com.rznkolds.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.rznkolds.domain.model.CommentUI

class CommentDiffUtil(
    private val oldList: List<CommentUI>,
    private val newList: List<CommentUI>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].uid == newList[new].uid)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}