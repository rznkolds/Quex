package com.rznkolds.presentation.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.presentation.common.load
import com.rznkolds.presentation.databinding.CommentItemBinding
import com.rznkolds.presentation.utils.CommentDiffUtil

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.AdapterHolder>() {

    var onShowMenuClick: (View, CommentUI) -> Unit = { view: View, comment: CommentUI -> }
    var onShowAnswersClick: (CommentUI) -> Unit = {}
    var onShowProfileClick: (CommentUI) -> Unit = {}
    private val auth by lazy { Firebase.auth }
    private var list = ArrayList<CommentUI>()

    inner class AdapterHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: CommentUI) {

            with(binding) {
                commentName.text = comment.name
                commentText.text = comment.comment

                commentAnswers.showAnswers(comment)
                commentPicture.showProfile(comment)
                commentItem.showAnswers(comment)
                commentText.showAnswers(comment)
                commentName.showProfile(comment)

                comment.profile?.let { commentPicture.load(it) }

                if (comment.uid == auth.uid.toString()) {

                    commentMenu.setOnClickListener { onShowMenuClick(it, comment) }
                } else {
                    commentMenu.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {

        return AdapterHolder(
            CommentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<CommentUI>) {

        list.clear()
        val result = DiffUtil.calculateDiff(CommentDiffUtil(list, newList))
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    // Click function to show answers fragment

    private fun View.showAnswers(comment: CommentUI) {

        setOnClickListener { onShowAnswersClick(comment) }
    }

    // Click function to show profile fragment

    private fun View.showProfile(comment: CommentUI) {

        setOnClickListener { onShowProfileClick(comment) }
    }
}