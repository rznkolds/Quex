package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Coin
import com.rk.quex.data.model.Comment

class CommentDiffUtil(

    private val oldList: ArrayList<Comment>,
    private val newList: ArrayList<Comment>

) : DiffUtil.Callback() {

    override fun areItemsTheSame(old: Int, new: Int): Boolean {

        return (oldList[old].uid == newList[new].uid)
    }

    override fun areContentsTheSame(old: Int, new: Int): Boolean {

        return when {

            oldList[old].uid != newList[new].uid -> {

                false
            }

            oldList[old].name != newList[new].name -> {

                false
            }

            oldList[old].url != newList[new].url -> {

                false
            }

            oldList[old].coin != newList[new].coin -> {

                false
            }

            oldList[old].comment != newList[new].comment -> {

                false
            }

            oldList[old].date != newList[new].date -> {

                false
            }

            oldList[old].time != newList[new].time -> {

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