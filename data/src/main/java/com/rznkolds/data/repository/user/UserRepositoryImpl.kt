package com.rznkolds.data.repository.user

import android.net.Uri
import com.rznkolds.data.source.remote.RemoteDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val source: RemoteDataSource
) : UserRepository {

    override suspend fun login(email: String, password: String) = source.login(email, password)

    override suspend fun register(
        name: String,
        description: String,
        email: String,
        password: String,
        picture: Uri
    ) = source.register(name, description, email, password, picture)

    override suspend fun getPicture() = source.getPicture()

    override suspend fun getProfile(uid: String) = source.getProfile(uid)

    override suspend fun signOut() = source.signOut()
}