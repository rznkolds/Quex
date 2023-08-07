package com.rznkolds.presentation.ui.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rznkolds.domain.common.collect
import com.rznkolds.presentation.R
import com.rznkolds.presentation.common.checkNetwork
import com.rznkolds.presentation.common.showToast
import com.rznkolds.presentation.common.viewBinding
import com.rznkolds.presentation.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val viewModel: SignInViewModel by viewModels()
    private val auth by lazy { Firebase.auth.currentUser }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        if (requireContext().checkNetwork()) {

            auth?.uid?.let {
                findNavController().navigate(SignInFragmentDirections.actionSignInToHome())
            }
        }

        binding.goHomeFromSignIn.setOnClickListener {

            login(
                binding.signInEmail.text.toString(),
                binding.signInPassword.text.toString()
            )
        }

        binding.goSignUp.setOnClickListener {

            findNavController().navigate(SignInFragmentDirections.actionSignInToSignUp())
        }
    }

    private fun initObservers() {

        viewModel.state.collect(lifecycleScope) {

            if (it.logged != null && it.logged != false) {

                findNavController().navigate(SignInFragmentDirections.actionSignInToHome())
            } else {
                requireContext().showToast("Hatalı e-mail veya şifre")
            }

            if (it.fail != null) {

                requireContext().showToast(it.fail)
            }
        }
    }

    private fun login(email : String,password:String) = viewModel.login(email,password)
}