package com.rk.quex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rk.quex.data.model.Coin
import com.rk.quex.databinding.CoinItemBinding
import com.rk.quex.pieces.HomeDirections
import com.rk.quex.utils.CoinDiffUtil

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.AdapterHolder>() {

    private var list = ArrayList<Coin>()

    inner class AdapterHolder(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {

        return AdapterHolder(

            CoinItemBinding.inflate(

                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        val current = list[position]

        Glide.with(holder.itemView).load(current.image).into(holder.binding.coinPicture)

        holder.binding.itemName.text = current.name

        holder.binding.itemPrice.text = current.current_price

        holder.itemView.setOnClickListener {

            it.findNavController().navigate(

                HomeDirections.actionHomeToComments(
                    current.name,
                    current.image,
                    current.current_price
                )
            )
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun setData(new_user_list: ArrayList<Coin>) {

        val result = DiffUtil.calculateDiff(CoinDiffUtil(list, new_user_list))

        list.addAll(new_user_list)

        result.dispatchUpdatesTo(this)
    }
}