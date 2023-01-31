package com.rk.quex.ui.signup

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rk.quex.R
import com.rk.quex.common.setPicture
import com.rk.quex.common.showToast
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var dialog : AlertDialog
    private var picture: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pictureProfile.setOnClickListener {
            intent()
        }

        binding.pictureAdd.setOnClickListener {
            intent()
        }

        binding.signUp.setOnClickListener {

            val builder = AlertDialog.Builder( this.requireContext() ).apply {
                this.setView(layoutInflater.inflate(R.layout.custom_alert_dialog, null))
                this.setCancelable(false)
            }

            dialog = builder.create().also {
                it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it.window?.setLayout(200,200)
                it.show()
            }

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
                dialog.dismiss()
                this.findNavController().navigate(SignUpFragmentDirections.actionSignUpToHome())
            } else {
                dialog.dismiss()
                requireContext().showToast("E-mail zaten mevcut")
            }
        }

        viewModel.fail.observe(viewLifecycleOwner) {
            dialog.dismiss()
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

    private fun intent() {

        Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            register.launch(this)
        }
    }

    private val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            picture = it.data?.data
            binding.pictureProfile.setPicture(it.data?.data.toString())
            binding.pictureAdd.visibility = View.INVISIBLE
        }
    }
}