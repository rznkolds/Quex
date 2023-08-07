package com.rznkolds.quex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rznkolds.quex.common.viewBinding
import com.rznkolds.quex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}