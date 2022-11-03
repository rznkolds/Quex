package com.rk.quex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rk.quex.common.viewBinding
import com.rk.quex.databinding.ActivityMainBinding
import com.rk.quex.databinding.FragmentSignUpBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}