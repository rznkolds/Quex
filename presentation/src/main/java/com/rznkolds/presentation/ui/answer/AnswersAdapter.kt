package com.rznkolds.presentation.ui.answer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.presentation.common.load
import com.rznkolds.presentation.databinding.AnswerItemBinding
import com.rznkolds.presentation.utils.AnswerDiffUtil

class AnswersAdapter : RecyclerView.Adapter<AnswersAdapter.AdapterHolder>() {

    var onShowMenuClick: (View, AnswerUI) -> Unit = { _: View, _: AnswerUI -> }
    var onShowProfileClick: (String) -> Unit = { }
    private val auth by lazy { Firebase.auth }
    private var list = ArrayList<AnswerUI>()

    inner class AdapterHolder(val binding: AnswerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(answer: AnswerUI) {

            with(binding) {

                answerName.text = answer.name
                answerText.text = answer.comment
                answer.profile?.let { answerPicture.load(it) }

                if (answer.uid == auth.uid.toString()) {

                    answerMenu.setOnClickListener { onShowMenuClick(it, answer) }
                } else {
                    answerMenu.visibility = View.GONE
                }

                itemView.setOnClickListener {

                    answer.uid?.let { uid -> onShowProfileClick(uid) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersAdapter.AdapterHolder {

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

    fun setData(newList: List<AnswerUI>) {
        list.clear()
        val result = DiffUtil.calculateDiff(AnswerDiffUtil(list, newList))
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}