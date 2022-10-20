package com.rk.quex.utils

import androidx.recyclerview.widget.DiffUtil
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Comment

class AnswerDiffUtil(

    private val oldList: ArrayList<Answer>,
    private val newList: ArrayList<Answer>

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

            oldList[old].above_uid != newList[new].above_uid -> {

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

            oldList[old].top_date  != newList[new].top_date -> {

                false
            }

            oldList[old].top_time != newList[new].top_time -> {

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