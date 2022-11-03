package com.rk.quex.common

import android.content.Context
import android.net.ConnectivityManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun Context.checkNetwork(): Boolean {
    val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val active = connectivity.activeNetwork
    return active != null
}

fun ImageView.setPicture(url: String) {
    Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun EditText.endsWith(): Boolean {
    return !this.text.endsWith("ytd") || !this.text.endsWith("ytd.") || !this.text.endsWith("YTD") || !this.text.endsWith("YTD.")
}

