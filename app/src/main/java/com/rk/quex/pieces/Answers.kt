package com.rk.quex.pieces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rk.quex.R
import com.rk.quex.databinding.FragmentAnswersBinding
import com.rk.quex.databinding.FragmentCommentsBinding

class Answers : Fragment() {

    private lateinit var binding: FragmentAnswersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAnswersBinding.inflate(inflater, container, false)

        return binding.root
    }
}