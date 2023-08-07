package com.rznkolds.data.repository.authenticator

import android.net.Uri

interface Authenticator {

    suspend fun getUid(): String

    suspend fun register(email: String, password: String)

    suspend fun login(email: String, password: String): Boolean

    suspend fun setPicture(picture: Uri): String

    suspend fun getPicture(): Uri

    suspend fun signOut()
}