package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Coin

class CoinDiffUtil(

    private val oldList: ArrayList<Coin>,
    private val newList: ArrayList<Coin>

) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int): Boolean {

        return (oldList[old].name == newList[new].name)
    }

    override fun areContentsTheSame(old: Int, new: Int): Boolean {

        return when {

            oldList[old].name != newList[new].name -> {

                false
            }

            oldList[old].image != newList[new].image -> {

                false
            }

            oldList[old].current_price != newList[new].current_price -> {

                false
            }

            else -> true
        }
    }

    override fun getOldListSize(): Int {

        return oldList.size
    }

    override fun getNewListSize(): Int {

        return newList.size
    }
}