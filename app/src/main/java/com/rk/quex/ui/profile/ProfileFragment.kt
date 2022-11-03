package com.rk.quex.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rk.quex.R
import com.rk.quex.common.setPicture
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

    private val adapter by lazy { FavoriteAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
    }

    private fun initObservers() {

        with(binding) {

            viewModel.informations.observe(viewLifecycleOwner) {
                if (it != null) {
                    it.url?.let { it1 ->
                        profilePicture.setPicture(it1)
                    }
                    profileName.text = it.name
                    profileDescription.text = it.description
                }
            }

            viewModel.favorites.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())
                    favoritesRecycler.adapter = adapter
                    adapter.setData(it)
                }
            }
        }
    }
}