package com.rk.quex.ui.enter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.R
import com.rk.quex.databinding.FragmentEnterBinding
import com.rk.quex.network.Connection
import com.rk.quex.common.viewBinding

class Enter : Fragment(R.layout.fragment_enter) {

    private val binding by viewBinding(FragmentEnterBinding::bind)
    private val auth by lazy { Firebase.auth.currentUser }
    private val viewModel: EnterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val network = Connection().checkNetwork(this.requireContext())

        if (network) {

            auth?.uid?.let {

                val direction = EnterDirections.actionEnterToHome()

                this.findNavController().navigate(direction)
            }
        }

        binding.enter.setOnClickListener {

            login()
        }

        binding.createScreen.setOnClickListener {

            val direction = EnterDirections.actionEnterToCreate()

            this.findNavController().navigate(direction)
        }
    }

    private fun login() {

        viewModel.login(
            binding.enterEmail.text.toString(),
            binding.enterPassword.text.toString()
        )

        viewModel.result.observe(viewLifecycleOwner) {

            if (it) {

                this.findNavController().navigate(EnterDirections.actionEnterToHome())

            } else {

                toast("Hatalı e-mail veya şifre")
            }
        }
    }

    private fun toast(text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}