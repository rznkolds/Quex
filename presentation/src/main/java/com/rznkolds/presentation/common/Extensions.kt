package com.rznkolds.presentation.common

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

// VIEW BINDING DELEGATE

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline factory: (LayoutInflater) -> T) = lazy(
    LazyThreadSafetyMode.NONE) {
    factory(layoutInflater)
}

fun <T : ViewBinding> Fragment.viewBinding(factory: (View) -> T): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T>,
    DefaultLifecycleObserver {

    private var binding: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T = binding ?: factory(requireView()).also {

        if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            viewLifecycleOwner.lifecycle.addObserver(this)
            binding = it
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        binding = null
    }
}

// FRAGMENT EXTENSIONS

fun Context.checkNetwork(): Boolean {
    val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val active = connectivity.activeNetwork
    return active != null
}

fun ImageView.load(url: String) {

    Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun EditText.endsWith(): Boolean {
    return !this.text.endsWith("ytd") || !this.text.endsWith("ytd.") || !this.text.endsWith("YTD") || !this.text.endsWith("YTD.")
}

