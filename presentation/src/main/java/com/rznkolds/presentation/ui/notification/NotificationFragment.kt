package com.rznkolds.presentation.ui.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rznkolds.domain.common.collect
import com.rznkolds.presentation.R
import com.rznkolds.presentation.common.showToast
import com.rznkolds.presentation.common.viewBinding
import com.rznkolds.presentation.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : Fragment(R.layout.fragment_notification) {

    private val binding by viewBinding(FragmentNotificationBinding::bind)
    private val viewModel: NotificationViewModel by viewModels()
    private val adapter by lazy { NotificationAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        binding.notificationDelete.setOnClickListener { deleteNotifications() }

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

        viewModel.state.collect(lifecycleScope) {

            if (!it.notifications.isNullOrEmpty()) {

                notificationRecycler.layoutManager = LinearLayoutManager(requireContext())
                notificationRecycler.adapter = adapter
                adapter.setData(it.notifications)
            }

            if (it.deleted != null && it.deleted == true) {

                viewModel.getNotifications()
                requireContext().showToast("Bildirimler Silindi")
            } else {
                requireContext().showToast("Bildirim bulunamadı")
            }
        }
    }

    private fun deleteNotifications() = viewModel.deleteNotifications()
}