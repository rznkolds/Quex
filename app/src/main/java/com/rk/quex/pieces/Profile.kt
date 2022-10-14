package com.rk.quex.pieces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rk.quex.adapter.FavoriteAdapter
import com.rk.quex.databinding.FragmentProfileBinding
import com.rk.quex.viewmodels.ProfileViewModel

class Profile : Fragment() {

    private val adapter by lazy { FavoriteAdapter(this.requireContext()) }
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.favoritesRecycler.adapter = adapter

        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.informations.observe(this.requireActivity()) {

            binding.profileName.text = it.name
            binding.profileDescription.text = it.text
            Glide.with(this).load(it.url).into(binding.profilePicture)
        }

        viewModel.favorites.observe(this.requireActivity()) {

            if (!it.isNullOrEmpty()) {

                adapter.setData(it)
                binding.favoriteWarning.visibility = View.GONE

            } else {

                binding.favoriteWarning.visibility = View.VISIBLE
                binding.favoritesRecycler.visibility = View.INVISIBLE
            }
        }
    }
}