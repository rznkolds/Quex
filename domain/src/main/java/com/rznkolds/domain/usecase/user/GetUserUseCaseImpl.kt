package com.rznkolds.domain.usecase.user

import com.rznkolds.data.dto.User
import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.UserUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
    private val mapper: BaseMapper<User, UserUI>
) : GetUserUseCase {

    override operator fun invoke(uid:String): Flow<Resource<UserUI>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repository.getProfile(uid))))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}