package com.rk.quex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rk.quex.model.CoinModel
import com.rk.quex.repository.Repository
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelFactory = MainViewModelFactory ( Repository() )

        val txt = findViewById<TextView>(R.id.txt)

        txt.setOnClickListener {

            val viewModel = ViewModelProvider(this, viewModelFactory )[MainViewModel::class.java]

            viewModel.getPost("00c182b523ad4000660b609c70c31619301a1ebe").toString()

            viewModel.response.observe(this, Observer {

                it.enqueue( object : Callback<List<CoinModel>> { // Bu kısımda enqueue metodu ile bir istek belirledik .

                    override fun onResponse(call: Call<List<CoinModel>>, response: Response<List<CoinModel>>) {

                        if ( response.isSuccessful ) {

                            txt.text = response.body()?.get(0)?.currency
                        }
                    }

                    override fun onFailure(call: Call<List<CoinModel>>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            })
        }
    }
}