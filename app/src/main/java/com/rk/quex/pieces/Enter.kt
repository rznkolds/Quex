package com.rk.quex.pieces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.databinding.FragmentEnterBinding
import com.rk.quex.network.Connection
import com.rk.quex.viewmodels.EnterViewModel

class Enter : Fragment() {

    private val auth by lazy { Firebase.auth.currentUser }
    private val viewModel: EnterViewModel by viewModels()
    private lateinit var binding: FragmentEnterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEnterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val network = Connection().checkNetwork(this.requireContext())

        if (network) {

            auth?.uid?.let {

                val direction = EnterDirections.actionEnterToHome()

                this.findNavController().navigate(direction)

            } ?: {

                toast("Giriş yapınız")
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

        viewModel.userLogin(
            binding.enterEmail.text.toString(),
            binding.enterPassword.text.toString()
        )

        viewModel.result.observe(viewLifecycleOwner) {

            if (it == true) {

                val direction = EnterDirections.actionEnterToHome()

                this.findNavController().navigate(direction)

            } else {

                toast("Hatalı e-mail veya şifre")
            }
        }
    }

    private fun toast(text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}