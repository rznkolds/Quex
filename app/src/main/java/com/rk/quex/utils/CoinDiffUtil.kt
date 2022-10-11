package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Coin

class CoinDiffUtil(private val old_list: ArrayList<Coin>, private val new_list: ArrayList<Coin>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int): Boolean {

        return (old_list[old].name == new_list[new].name)
    }

    override fun areContentsTheSame(old: Int, new: Int): Boolean {

        return when {

            old_list[old].name != new_list[new].name -> {

                false
            }

            old_list[old].image != new_list[new].image -> {

                false
            }

            old_list[old].current_price != new_list[new].current_price -> {

                false
            }

            else -> true
        }
    }

    override fun getOldListSize(): Int {

        return old_list.size
    }

    override fun getNewListSize(): Int {

        return new_list.size
    }
}