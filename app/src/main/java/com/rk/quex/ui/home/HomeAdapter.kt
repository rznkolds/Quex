package com.rk.quex.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.quex.common.setPicture
import com.rk.quex.data.model.Coin
import com.rk.quex.databinding.CoinItemBinding
import com.rk.quex.utils.CoinDiffUtil

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.AdapterHolder>() {

    private var list = ArrayList<Coin>()
    var onCoinClick: (Coin) -> Unit = {}

    inner class AdapterHolder(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin) = with(binding) {

            coin.picture?.let {
                coinPicture.setPicture(it)
            }
            coinName.text = coin.name
            coinPrice.text = coin.price

            itemView.setOnClickListener {
                onCoinClick(coin)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            CoinItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setData(new_user_list: ArrayList<Coin>) {
        list.clear()
        val result = DiffUtil.calculateDiff(CoinDiffUtil(list, new_user_list))
        list.addAll(new_user_list)
        result.dispatchUpdatesTo(this)
    }
}