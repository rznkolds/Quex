package com.rznkolds.domain.usecase.account

import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.domain.common.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
) : SignInUseCase {

    override operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(repository.login(email, password)))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}