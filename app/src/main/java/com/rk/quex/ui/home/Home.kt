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

class Home : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { CoinAdapter() }
    private val auth by lazy { Firebase.auth.currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onCoinClick = {
            if (it.name != null && it.image != null && it.current_price != null) {
                findNavController().navigate(
                    HomeDirections.actionHomeToComments(
                        it.name, it.image, it.current_price
                    )
                )
            }
        }

        binding.homeProfile.setOnClickListener {
            auth?.uid?.let {
                findNavController().navigate(HomeDirections.actionHomeToProfile(it))
            }
        }

        initObservers()
    }

    private fun initObservers() {

        with(binding) {
            viewModel.coins.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    coinRecycler.layoutManager = LinearLayoutManager(requireContext())
                    coinRecycler.adapter = adapter
                    adapter.setData(it)
                }
            }

            viewModel.picture.observe(viewLifecycleOwner) {
                homeProfile.setPicture(it.toString())
            }
        }
    }
}