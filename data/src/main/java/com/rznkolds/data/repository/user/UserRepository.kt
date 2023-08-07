package com.rznkolds.data.repository.user

import android.net.Uri
import com.rznkolds.data.dto.Status
import com.rznkolds.data.dto.User

interface UserRepository {

    suspend fun login(email: String, password: String):Boolean

    suspend fun register(
        name: String,
        description: String,
        email: String,
        password: String,
        picture: Uri
    ): Status

    suspend fun getPicture(): Uri

    suspend fun getProfile(uid: String): User

    suspend fun signOut()
}