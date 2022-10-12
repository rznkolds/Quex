package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Coin
import com.rk.quex.data.model.Favorite

class FavoriteDiffUtil(

    private val old_list: ArrayList<Favorite>,
    private val new_list: ArrayList<Favorite>

) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int): Boolean {

        return (old_list[old].uid == new_list[new].uid)
    }

    override fun areContentsTheSame(old: Int, new: Int): Boolean {

        return when {

            old_list[old].coin != new_list[new].coin -> {

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