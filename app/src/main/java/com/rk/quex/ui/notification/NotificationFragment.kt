package com.rk.quex.ui.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rk.quex.R
import com.rk.quex.common.showToast
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment(R.layout.fragment_notification) {

    private val binding by viewBinding(FragmentNotificationBinding::bind)
    private val viewModel: NotificationViewModel by viewModels()
    private val adapter by lazy { NotificationAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        binding.notificationDelete.setOnClickListener {
            deleteNotifications()
        }

        adapter.onNotificationClick = {
            findNavController().navigate(NotificationFragmentDirections.actionNotificationToAnswers(
                it.uid!!,
                it.coin!!,
                it.date!!,
                it.time!!
            ))
        }
    }

    private fun initObservers() = with(binding) {

        viewModel.result.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.getNotifications()

                requireContext().showToast("Bildirimler Silindi")
            } else {
                requireContext().showToast("Bildirim bulunamadı")
            }
        }

        viewModel.notifications.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                notificationRecycler.layoutManager = LinearLayoutManager(requireContext())
                notificationRecycler.adapter = adapter
                adapter.setData(it)
            }
        }
    }

    private fun deleteNotifications() = viewModel.deleteNotifications()
}