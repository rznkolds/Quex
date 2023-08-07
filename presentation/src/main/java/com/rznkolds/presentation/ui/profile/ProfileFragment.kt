package com.rznkolds.presentation.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rznkolds.domain.common.collect
import com.rznkolds.presentation.R
import com.rznkolds.presentation.common.load
import com.rznkolds.presentation.common.viewBinding
import com.rznkolds.presentation.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

    private val adapter by lazy { ProfileAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.outBtn.setOnClickListener {
            viewModel.outProfile()
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileToSignIn()
            )
        }

        initObservers()
    }

    private fun initObservers() = with(binding) {

        viewModel.state.collect(lifecycleScope) {

            if (it.user != null) {

                profileName.text = it.user.name
                profileDescription.text = it.user.description
                it.user.profile?.let { v -> profilePicture.load(v) }
            }

            if (it.favorites != null) {

                favoritesRecycler.layoutManager = LinearLayoutManager(requireContext())
                favoritesRecycler.adapter = adapter
                adapter.setData(it.favorites)
            }
        }
    }
}