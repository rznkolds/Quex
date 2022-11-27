package com.rk.quex.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.R
import com.rk.quex.common.setPicture
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.FragmentHomeBinding

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
                    HomeFragmentDirections.actionHomeToComments(
                        it.name, it.picture, it.price
                    )
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

        viewModel.coins.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                coinRecycler.adapter = adapter
                adapter.setData(it)
                return@observe
            }

        }

        viewModel.picture.observe(viewLifecycleOwner) {
            homeProfile.setPicture(it.toString())
        }
    }
}