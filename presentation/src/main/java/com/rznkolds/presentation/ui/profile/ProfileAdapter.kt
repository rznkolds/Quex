package com.rznkolds.presentation.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rznkolds.domain.model.FavoriteUI
import com.rznkolds.presentation.databinding.FavoriteItemBinding
import com.rznkolds.presentation.utils.ProfileDiffUtil

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.AdapterHolder>() {

    private var list = ArrayList<FavoriteUI>()

    inner class AdapterHolder(val binding: FavoriteItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: FavoriteUI) {

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

    fun setData(newList: List<FavoriteUI>) {
        list.clear()
        val result = DiffUtil.calculateDiff(ProfileDiffUtil(list, newList))
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}