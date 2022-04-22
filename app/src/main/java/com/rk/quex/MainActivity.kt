package com.rk.quex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rk.quex.repository.Repository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = MainViewModelFactory ( Repository() )



        val txt = findViewById<TextView>(R.id.txt)

        txt.setOnClickListener {

            val viewModel = ViewModelProvider(this, viewModelFactory )[MainViewModel::class.java]

            viewModel.getPost("00c182b523ad4000660b609c70c31619301a1ebe")

            viewModel.response.observe(this, Observer {

                if (it.isSuccessful) {

                    txt.text = it.body()?.get(0)?.currency


                }
            })
        }


    }
}