package com.rk.quex.pieces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rk.quex.adapter.CoinAdapter
import com.rk.quex.databinding.FragmentHomeBinding
import com.rk.quex.viewmodels.HomeViewModel

class Home : Fragment() {

    private val adapter by lazy { CoinAdapter(this.requireContext()) }
    private val cloud by lazy { Firebase.storage.reference }
    private val auth by lazy { Firebase.auth }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.coinRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.coinRecycler.adapter = adapter

        cloud.child(auth.currentUser?.uid.toString()).downloadUrl.addOnSuccessListener {

            Glide.with(this).load(it.toString()).into(binding.homeProfile)
        }

        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.coins().observe(this.requireActivity()) {

            if (it.isNotEmpty()) {

                adapter.setData(it)
            }
        }

        return binding.root
    }
}