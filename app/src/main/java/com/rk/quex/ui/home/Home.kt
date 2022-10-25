package com.rk.quex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.databinding.FragmentHomeBinding

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { CoinAdapter() }
    private val auth by lazy { Firebase.auth.currentUser }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.coins.observe(viewLifecycleOwner) {

            if (!it.isNullOrEmpty()) {

                binding.coinRecycler.layoutManager = LinearLayoutManager(requireContext())
                binding.coinRecycler.adapter = adapter
                adapter.setData(it)
            }
        }

        viewModel.picture.observe(viewLifecycleOwner) {

            Glide.with(this)
                .load(it)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.homeProfile)
        }

        binding.homeProfile.setOnClickListener {

            auth?.uid?.let {

                val direction = HomeDirections.actionHomeToProfile(it)

                findNavController().navigate(direction)
            }
        }
    }
}