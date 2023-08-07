package com.rznkolds.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.rznkolds.domain.model.CoinUI

class CoinDiffUtil(
    private val oldList: List<CoinUI>,
    private val newList: List<CoinUI>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int) = (oldList[old].name == newList[new].name)

    override fun areContentsTheSame(old: Int, new: Int) = oldList[old] == newList[new]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}