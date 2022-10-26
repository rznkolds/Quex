package com.rk.quex.ui.create

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
import com.rk.quex.databinding.FragmentCreateBinding
import com.rk.quex.common.viewBinding

class Create : Fragment(R.layout.fragment_create) {

    private val binding by viewBinding(FragmentCreateBinding::bind)
    private val viewModel: CreateViewModel by viewModels()
    private var picture: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == Activity.RESULT_OK) {

                picture = it.data?.data

                Glide.with(this)
                    .load(picture)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.accountProfile)
            }
        }

        binding.accountProfile.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {

                type = "image/*"
            }

            register.launch(intent)
        }

        binding.create.setOnClickListener {

            createUser()
        }

        binding.enterScreen.setOnClickListener {

            val direction = CreateDirections.actionCreateToEnter()

            this.findNavController().navigate(direction)
        }
    }

    private fun createUser() {

        if (binding.accountProfile.drawable != null) {

            if (binding.accountName.text.isNotEmpty() &&
                binding.accountText.text.isNotEmpty() &&
                binding.accountEmail.text.isNotEmpty() &&
                binding.accountPassword.text.isNotEmpty()
            ) {

                viewModel.register(
                    binding.accountName.text.toString(),
                    binding.accountText.text.toString(),
                    binding.accountEmail.text.toString(),
                    binding.accountPassword.text.toString(),
                    picture!!
                )

                viewModel.result.observe(viewLifecycleOwner) {

                    if (it.equals(true)) {

                        val direction = CreateDirections.actionCreateToHome()

                        this.findNavController().navigate(direction)

                    } else {

                        toast("Kullanıcı verileri eksik")
                    }
                }

            } else {

                toast("Bos alanları doldurun")
            }

        } else {

            toast("Bir resim belirtin")
        }
    }

    private fun toast(text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}