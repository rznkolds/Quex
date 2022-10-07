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
import com.rkapp.fakechat.control.Connection

class Enter : Fragment() {

    private lateinit var binding: FragmentEnterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentEnterBinding.inflate(inflater, container, false)

        val network = Connection().network(this.requireContext())

        val user = Firebase.auth.currentUser

        if (network) {

            if (user != null) {

                this.findNavController().navigate(R.id.action_enter_to_home)
            }

        } else {

            Toast.makeText(this.requireContext(), "İnternet bağlantınızı kontrol ediniz", Toast.LENGTH_SHORT).show()
        }

        binding.enter.setOnClickListener {

            login()
        }

        binding.createScreen.setOnClickListener {

            this.findNavController().navigate(R.id.action_enter_to_create)
        }

        return binding.root
    }

    private fun login() {

        val viewModel = ViewModelProvider(this)[EnterViewModel::class.java]

        viewModel.loginUser(binding.enterEmail.text.toString(), binding.enterPassword.text.toString()).observe(this.requireActivity()) {

            if (it == true) {

                this.findNavController().navigate(R.id.action_enter_to_home)

            } else {

                Toast.makeText(this.requireContext(), "Hatalı E-mail veya Şifre", Toast.LENGTH_SHORT).show()
            }
        }
    }
}