package com.rk.quex.pieces

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.adapter.CoinAdapter
import com.rk.quex.adapter.FavoriteAdapter
import com.rk.quex.databinding.FragmentProfileBinding
import com.rk.quex.viewmodels.ProfileViewModel

class Profile : Fragment() {

    private val adapter by lazy { FavoriteAdapter(this.requireContext()) }
    private val auth by lazy { Firebase.auth.currentUser }
    private lateinit var binding: FragmentProfileBinding
    private val args: ProfileArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.favoritesRecycler.adapter = adapter

        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.user(args.uid).observe(this.requireActivity()) {

            binding.profileName.text = it.name
            binding.profileDescription.text = it.text
            Glide.with(this).load(it.url).into(binding.profilePicture)
        }

        viewModel.coins(auth!!.uid).observe(this.requireActivity()) {

            if (!it.isNullOrEmpty()) {

                adapter.setData(it)
                binding.favoriteWarning.visibility = View.GONE

            } else {

                binding.favoriteWarning.visibility = View.VISIBLE
                binding.favoritesRecycler.visibility = View.INVISIBLE
            }
        }

        return binding.root
    }
}