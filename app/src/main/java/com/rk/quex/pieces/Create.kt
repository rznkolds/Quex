package com.rk.quex.pieces

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rk.quex.R
import com.rk.quex.databinding.FragmentCreateBinding
import com.rk.quex.viewmodels.CreateViewModel

class Create : Fragment() {

    private lateinit var binding: FragmentCreateBinding
    private var picture: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentCreateBinding.inflate(inflater, container, false)

        val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == Activity.RESULT_OK) {

                picture = it.data?.data

                Glide.with(this).load(picture).diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.accountProfile)
            }
        }

        binding.accountProfile.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {

                type = "image/*"
            }

            register.launch(intent)
        }

        binding.create.setOnClickListener {

            if (binding.accountProfile.drawable != null) {

                if (binding.accountName.text.isNotEmpty() &&
                    binding.accountText.text.isNotEmpty() &&
                    binding.accountEmail.text.isNotEmpty() &&
                    binding.accountPassword.text.isNotEmpty()
                ) {

                    val vm = ViewModelProvider(this)[CreateViewModel::class.java]

                    vm.createUser(
                        binding.accountName.text.toString(),
                        binding.accountText.text.toString(),
                        binding.accountEmail.text.toString(),
                        binding.accountPassword.text.toString(),
                        picture!!
                    )

                    vm.result.observe(this.requireActivity()) {

                        if (it.equals(true)) {

                            this.findNavController().navigate(R.id.action_create_to_home)

                        } else {

                            toast("Kullanıcı Tanımlama Geçersiz")
                        }
                    }

                } else {

                    toast("Bos alanları doldurun")
                }

            } else {

                toast("Bir resim belirtin")
            }
        }

        binding.enterScreen.setOnClickListener {

            this.findNavController().navigate(R.id.action_create_to_enter)
        }

        return binding.root
    }

    private fun toast(text: String) {

        Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}