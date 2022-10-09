package com.rk.quex.pieces

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rk.quex.R
import com.rk.quex.databinding.FragmentHomeBinding
import com.rk.quex.viewmodels.HomeViewModel

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.coins().observe(this.requireActivity()){

           Log.e("Coin" , it[0].name )
        }

        return binding.root
    }
}