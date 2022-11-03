package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment

class AnswerDiffUtil(
    private val oldList: ArrayList<Answer>,
    private val newList: ArrayList<Answer>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].uid == newList[new].uid)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}