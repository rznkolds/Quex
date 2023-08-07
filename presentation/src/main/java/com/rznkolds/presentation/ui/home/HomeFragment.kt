package com.rznkolds.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rznkolds.domain.common.collect
import com.rznkolds.presentation.R
import com.rznkolds.presentation.common.load
import com.rznkolds.presentation.common.viewBinding
import com.rznkolds.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }
    private val auth by lazy { Firebase.auth.currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onCoinClick = {
            if (it.name != null && it.picture != null && it.price != null) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToComments(it.name!!, it.picture!!, it.price!!)
                )
            }
        }

        binding.homeProfile.setOnClickListener {
            auth?.uid?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeToProfile(it)
                )
            }
        }

        binding.notificationScreen.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeToNotification()
            )
        }

        initObservers()
    }

    private fun initObservers() = with(binding) {

        viewModel.state.collect(lifecycleScope) {

            if (!it.coins.isNullOrEmpty()) {

                coinRecycler.adapter = adapter
                adapter.setData(it.coins)
            }

            if (it.picture != null) {

                homeProfile.load(it.picture.toString())
            }
        }
    }
}