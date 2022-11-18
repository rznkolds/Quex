package com.rk.quex.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.quex.data.model.Favorite
import com.rk.quex.databinding.FavoriteItemBinding
import com.rk.quex.utils.ProfileDiffUtil

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.AdapterHolder>() {

    private var list = ArrayList<Favorite>()

    inner class AdapterHolder(val binding: FavoriteItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: Favorite) {
            binding.favoriteName.text = favorite.coin
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            FavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setData(new_user_list: ArrayList<Favorite>) {
        list.clear()
        val result = DiffUtil.calculateDiff(ProfileDiffUtil(list, new_user_list))
        list.addAll(new_user_list)
        result.dispatchUpdatesTo(this)
    }
}