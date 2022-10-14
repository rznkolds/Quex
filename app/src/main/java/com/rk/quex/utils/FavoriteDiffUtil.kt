package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Favorite

class FavoriteDiffUtil(

    private val oldList: ArrayList<Favorite>,
    private val newList: ArrayList<Favorite>

) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int): Boolean {

        return (oldList[old].uid == newList[new].uid)
    }

    override fun areContentsTheSame(old: Int, new: Int): Boolean {

        return when {

            oldList[old].coin != newList[new].coin -> {

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