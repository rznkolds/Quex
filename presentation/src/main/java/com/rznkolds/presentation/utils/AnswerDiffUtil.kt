package com.rznkolds.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.rznkolds.domain.model.AnswerUI

class AnswerDiffUtil(
    private val oldList: List<AnswerUI>,
    private val newList: List<AnswerUI>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].uid == newList[new].uid)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}