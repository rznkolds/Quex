package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Coin

class CoinDiffUtil(
    private val oldList: ArrayList<Coin>,
    private val newList: ArrayList<Coin>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].name == newList[new].name)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}