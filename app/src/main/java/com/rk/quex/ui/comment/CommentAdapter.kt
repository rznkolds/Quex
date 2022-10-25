package com.rk.quex.ui.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rk.quex.data.model.Comment
import com.rk.quex.databinding.CommentItemBinding
import com.rk.quex.utils.CommentDiffUtil

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.AdapterHolder>() {

    private var list = ArrayList<Comment>()

    inner class AdapterHolder(val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {

        return AdapterHolder(

            CommentItemBinding.inflate(

                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        val current = list[position]

        Glide.with(holder.itemView)
            .load(current.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.commentPicture)

        holder.binding.commentName.text = current.name
        holder.binding.commentText.text = current.comment

        holder.binding.commentName.setOnClickListener {

            showProfile(it, current)
        }

        holder.binding.commentPicture.setOnClickListener {

            showProfile(it, current)
        }

        holder.binding.commentText.setOnClickListener {

            showAnswers(it, current)
        }

        holder.binding.commentAnswers.setOnClickListener {

            showAnswers(it, current)
        }

        holder.binding.item.setOnClickListener {

            showAnswers(it, current)
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun setData(new_user_list: ArrayList<Comment>) {

        list.clear()

        val result = DiffUtil.calculateDiff(CommentDiffUtil(list, new_user_list))

        list.addAll(new_user_list)

        result.dispatchUpdatesTo(this)
    }

    private fun showAnswers(view: View, current: Comment) {

        view.findNavController().navigate(

            CommentsDirections.actionCommentsToAnswers(
                current.uid,
                current.name,
                current.url,
                current.coin,
                current.comment,
                current.date,
                current.time
            )
        )
    }

    private fun showProfile(view: View, current: Comment) {

        view.findNavController().navigate(

            CommentsDirections.actionCommentsToProfile(
                current.uid
            )
        )
    }
}