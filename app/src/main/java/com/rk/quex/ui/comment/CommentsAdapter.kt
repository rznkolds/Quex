package com.rk.quex.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.common.setPicture
import com.rk.quex.data.model.Comment
import com.rk.quex.databinding.CommentItemBinding
import com.rk.quex.utils.CommentDiffUtil

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.AdapterHolder>() {

    private val auth by lazy { Firebase.auth }
    private var list = ArrayList<Comment>()
    var onShowAnswersClick: (Comment) -> Unit = {}
    var onShowProfileClick: (Comment) -> Unit = {}
    var onShowMenuClick: (View,Comment) -> Unit = { view: View, comment: Comment -> }

    inner class AdapterHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {

            with(binding) {
                commentName.text = comment.name
                commentText.text = comment.comment

                commentItem.showAnswers(comment)
                commentText.showAnswers(comment)
                commentAnswers.showAnswers(comment)
                commentPicture.showProfile(comment)
                commentName.showProfile(comment)

                comment.profile?.let {
                    commentPicture.setPicture(it)
                }

                if ( comment.uid == auth.uid.toString() ) {
                    commentMenu.setOnClickListener {
                        onShowMenuClick(it,comment)
                    }
                }else{
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

    fun setData(new_user_list: ArrayList<Comment>) {
        list.clear()
        val result = DiffUtil.calculateDiff(CommentDiffUtil(list, new_user_list))
        list.addAll(new_user_list)
        result.dispatchUpdatesTo(this)
    }

    // Click function to show answers fragment

    private fun View.showAnswers(comment: Comment) {
        setOnClickListener {
            onShowAnswersClick(comment)
        }
    }

    // Click function to show profile fragment

    private fun View.showProfile(comment: Comment) {
        setOnClickListener {
            onShowProfileClick(comment)
        }
    }
}