package com.rznkolds.presentation.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rznkolds.domain.model.NotificationUI
import com.rznkolds.presentation.common.load
import com.rznkolds.presentation.databinding.NotificationItemBinding
import com.rznkolds.presentation.utils.NotificationDiffUtil

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.AdapterHolder>() {

    private var list = ArrayList<NotificationUI>()
    var onNotificationClick: (NotificationUI) -> Unit = {}

    inner class AdapterHolder(val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: NotificationUI) {

            with(binding) {
                notificationName.text = notification.name
                notification.profile?.let { notificationPicture.load(it) }
                notificationDescription.text = (notification.coin + " hakkındaki yorumuna yanıt verdi.")

                itemView.setOnClickListener {
                    onNotificationClick(notification)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        return AdapterHolder(
            NotificationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<NotificationUI>) {
        list.clear()
        val result = DiffUtil.calculateDiff(NotificationDiffUtil(list, newList))
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}