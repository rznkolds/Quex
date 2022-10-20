package com.rk.quex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rk.quex.data.model.Answer
import com.rk.quex.data.model.Coin
import com.rk.quex.databinding.AnswerItemBinding
import com.rk.quex.databinding.CoinItemBinding
import com.rk.quex.pieces.AnswersDirections
import com.rk.quex.pieces.HomeDirections
import com.rk.quex.utils.AnswerDiffUtil
import com.rk.quex.utils.CoinDiffUtil

class AnswerAdapter : RecyclerView.Adapter<AnswerAdapter.AdapterHolder>() {

    private var list = ArrayList<Answer>()

    inner class AdapterHolder(val binding: AnswerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {

        return AdapterHolder(

            AnswerItemBinding.inflate(

                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        val current = list[position]

        Glide.with(holder.itemView).load(current.url ).into(holder.binding.answerPicture)

        holder.binding.answerName.text = current.name

        holder.binding.answerText.text = current.comment

        holder.itemView.setOnClickListener {

            it.findNavController().navigate(

                AnswersDirections.actionAnswersToProfile (
                    current.uid
                )
            )
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun setData(new_user_list: ArrayList<Answer>) {

        list.clear()

        val result = DiffUtil.calculateDiff(AnswerDiffUtil(list, new_user_list))

        list.addAll(new_user_list)

        result.dispatchUpdatesTo(this)
    }
}