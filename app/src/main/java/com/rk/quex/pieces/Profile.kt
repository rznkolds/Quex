package com.rk.quex.pieces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rk.quex.databinding.FragmentProfileBinding
import com.rk.quex.viewmodels.ProfileViewModel

class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val args: ProfileArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.user (args.uid).observe(this.requireActivity()) {

            binding.profileName.text = it.name
            binding.profileDescription.text = it.text
            Glide.with(this).load(it.url).into(binding.profilePicture)
        }

        return binding.root
    }
}