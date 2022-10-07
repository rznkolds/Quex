package com.rk.quex.pieces

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rk.quex.R
import com.rk.quex.databinding.FragmentCreateBinding
import com.rk.quex.databinding.FragmentEnterBinding

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



        binding.enterScreen.setOnClickListener {

            this.findNavController().navigate(R.id.action_create_to_enter)
        }

        return binding.root
    }
}