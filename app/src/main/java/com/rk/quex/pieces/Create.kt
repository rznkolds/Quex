package com.rk.quex.pieces

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rk.quex.R
import com.rk.quex.databinding.FragmentCreateBinding
import com.rk.quex.databinding.FragmentEnterBinding

class Create : Fragment() {

    private lateinit var binding : FragmentCreateBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentCreateBinding.inflate(inflater , container ,false )

        binding.enterScreen.setOnClickListener {

            this.findNavController().navigate(R.id.action_create_to_enter)
        }

        return binding.root
    }
}