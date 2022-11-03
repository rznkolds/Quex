package com.rk.quex.ui.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.R
import com.rk.quex.common.checkNetwork
import com.rk.quex.common.showToast
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val auth by lazy { Firebase.auth.currentUser }
    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        if (requireContext().checkNetwork()) {
            auth?.uid?.let {
                findNavController().navigate(SignInFragmentDirections.actionSignInToHome())
            }
        }

        binding.goHomeFromSignIn.setOnClickListener {
            login()
        }

        binding.goSignUp.setOnClickListener {
            this.findNavController().navigate(SignInFragmentDirections.actionSignInToSignUp())
        }
    }

    private fun initObservers() {

        viewModel.result.observe(viewLifecycleOwner) {
            if (it) {
                this.findNavController().navigate(SignInFragmentDirections.actionSignInToHome())
            } else {
                requireContext().showToast("Hatalı e-mail veya şifre")
            }
        }

        viewModel.failMessage.observe(viewLifecycleOwner) {
            requireContext().showToast(it)
        }
    }

    private fun login() {
        viewModel.login(binding.signInEmail.text.toString(), binding.signInPassword.text.toString())
    }
}