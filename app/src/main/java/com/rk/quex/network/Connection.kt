package com.rk.quex.network

import android.content.Context
import android.net.ConnectivityManager

class Connection {

    fun network(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val active = connectivityManager.activeNetwork

        return active != null
    }
}