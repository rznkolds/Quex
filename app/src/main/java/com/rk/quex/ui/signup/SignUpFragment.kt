package com.rk.quex.ui.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rk.quex.R
import com.rk.quex.common.setPicture
import com.rk.quex.common.showToast
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()
    private var picture: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpProfile.setOnClickListener {

            Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
                register.launch(this)
            }
        }

        binding.goHomeFromSignUp.setOnClickListener {
            register()
        }

        binding.goSignIn.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpToSignIn())
        }

        initObservers()
    }

    private fun initObservers() {

        viewModel.result.observe(viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(SignUpFragmentDirections.actionSignUpToHome())
            } else {
                requireContext().showToast("Kullanıcı verileri eksik")
            }
        }

        viewModel.failMessage.observe(viewLifecycleOwner) {
            requireContext().showToast(it)
        }
    }

    private fun register() {

        with(binding) {
            val name = signUpName.text.toString()
            val description = signUpDescription.text.toString()
            val email = signUpEmail.text.toString()
            val password = signUpPassword.text.toString()

            viewModel.register(name, description, email, password, picture)
        }
    }

    private val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                picture = it.data?.data
                binding.signUpProfile.setPicture(it.data?.data.toString())
            }
    }
}