package com.rk.quex.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rk.quex.databinding.FragmentProfileBinding

class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val adapter by lazy { FavoriteAdapter() }

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

        viewModel.informations.observe(viewLifecycleOwner) {

            if (it != null) {

                Glide.with(this)
                    .load(it.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.profilePicture)

                binding.profileName.text = it.name
                binding.profileDescription.text = it.text
            }
        }

        viewModel.favorites.observe(this.requireActivity()) {

            if (!it.isNullOrEmpty()) {

                binding.favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())
                binding.favoritesRecycler.adapter = adapter
                adapter.setData(it)
            }
        }
    }
}