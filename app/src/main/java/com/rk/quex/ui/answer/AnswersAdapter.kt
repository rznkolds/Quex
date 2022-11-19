package com.rk.quex.ui.answer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.common.setPicture
import com.rk.quex.data.model.Answer
import com.rk.quex.databinding.AnswerItemBinding
import com.rk.quex.utils.AnswerDiffUtil

class AnswersAdapter : RecyclerView.Adapter<AnswersAdapter.AdapterHolder>() {

    private val auth by lazy { Firebase.auth }
    private var list = ArrayList<Answer>()
    var onShowProfileClick: (String) -> Unit = {}
    var onShowMenuClick: (View, Answer) -> Unit = { view: View, answer: Answer -> }

    inner class AdapterHolder(val binding: AnswerItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(answer: Answer) {

            with(binding) {
                answerName.text = answer.name
                answerText.text = answer.comment

                answer.profile?.let {
                    answerPicture.setPicture(it)
                }

                if ( answer.uid == auth.uid.toString() ) {
                    answerMenu.setOnClickListener {
                        onShowMenuClick(it,answer)
                    }
                }else{
                    answerMenu.visibility = View.GONE
                }

                itemView.setOnClickListener {
                    answer.uid?.let { uid ->
                        onShowProfileClick(uid)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            AnswerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setData(newList: ArrayList<Answer>) {
        list.clear()
        val result = DiffUtil.calculateDiff(AnswerDiffUtil(list, newList))
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}