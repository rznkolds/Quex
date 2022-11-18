package com.rk.quex.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rk.quex.common.setPicture
import com.rk.quex.data.model.Notification
import com.rk.quex.databinding.NotificationItemBinding
import com.rk.quex.utils.NotificationDiffUtil

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.AdapterHolder>() {

    private var list = ArrayList<Notification>()
    var onNotificationClick: (Notification) -> Unit = {}

    inner class AdapterHolder(val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: Notification) {

            with(binding) {
                notification.profile?.let {
                    notificationPicture.setPicture(it)
                }
                notificationName.text = notification.name
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

    fun setData(newNotificationList: ArrayList<Notification>) {
        list.clear()
        val result = DiffUtil.calculateDiff(NotificationDiffUtil(list, newNotificationList))
        list.addAll(newNotificationList)
        result.dispatchUpdatesTo(this)
    }
}