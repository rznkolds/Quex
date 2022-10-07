package com.rk.quex.pieces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.rk.quex.R
import com.rk.quex.databinding.FragmentEnterBinding

class Enter : Fragment() {

    private lateinit var binding : FragmentEnterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentEnterBinding.inflate(inflater , container ,false )

        binding.createScreen.setOnClickListener {

            this.findNavController().navigate(R.id.action_enter_to_create)
        }

        return binding.root
    }
}