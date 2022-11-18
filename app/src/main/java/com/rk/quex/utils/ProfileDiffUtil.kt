package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Favorite

class ProfileDiffUtil(
    private val oldList: ArrayList<Favorite>,
    private val newList: ArrayList<Favorite>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].uid == newList[new].uid)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}