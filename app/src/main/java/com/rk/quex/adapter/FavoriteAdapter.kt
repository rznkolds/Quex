package com.rk.quex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.quex.data.model.Favorite
import com.rk.quex.databinding.FavoriteItemBinding
import com.rk.quex.utils.FavoriteDiffUtil

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.AdapterHolder>() {

    private var list = ArrayList<Favorite>()

    inner class AdapterHolder(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {

        return AdapterHolder(

            FavoriteItemBinding.inflate(

                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {

        val current = list[position]

        holder.binding.favoriteName.text = current.coin

    }

    override fun getItemCount(): Int {

        return list.size
    }

    fun setData(new_user_list: ArrayList<Favorite>) {

        val result = DiffUtil.calculateDiff(FavoriteDiffUtil(list, new_user_list))

        list.addAll(new_user_list)

        result.dispatchUpdatesTo(this)
    }
}