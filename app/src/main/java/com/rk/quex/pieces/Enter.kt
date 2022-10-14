package com.rk.quex.pieces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rk.quex.R
import com.rk.quex.databinding.FragmentEnterBinding
import com.rk.quex.viewmodels.EnterViewModel
import com.rk.quex.network.Connection

class Enter : Fragment() {

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

        val user = Firebase.auth.currentUser

        if (network) {

            if (user != null) {

                this.findNavController().navigate(R.id.action_enter_to_home)
            }

        } else {

            toast("İnternet bağlantınızı kontrol ediniz")
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

        val viewModel = ViewModelProvider(this)[EnterViewModel::class.java]

        viewModel.userLogin(
            binding.enterEmail.text.toString(),
            binding.enterPassword.text.toString()
        )

        viewModel.result.observe(this.requireActivity()) {

            if (it == true) {

                val direction = EnterDirections.actionEnterToHome()

                this.findNavController().navigate(direction)

            } else {

                toast("Hatalı E-mail veya Şifre")
            }
        }
    }

    private fun toast(text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}