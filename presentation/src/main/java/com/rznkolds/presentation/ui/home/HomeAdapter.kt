package com.rznkolds.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rznkolds.domain.model.CoinUI
import com.rznkolds.presentation.common.load
import com.rznkolds.presentation.databinding.CoinItemBinding
import com.rznkolds.presentation.utils.CoinDiffUtil

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.AdapterHolder>() {

    private var list = ArrayList<CoinUI>()
    var onCoinClick: (CoinUI) -> Unit = {}

    inner class AdapterHolder(val binding: CoinItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: CoinUI) = with(binding) {

            coin.picture?.let {
                coinPicture.load(it)
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

    fun setData(newList: List<CoinUI>) {
        list.clear()
        val result = DiffUtil.calculateDiff(CoinDiffUtil(list, newList))
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}