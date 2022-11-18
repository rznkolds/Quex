package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Notification

class NotificationDiffUtil (
private val oldList: ArrayList<Notification>,
private val newList: ArrayList<Notification>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].uid == newList[new].uid)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}