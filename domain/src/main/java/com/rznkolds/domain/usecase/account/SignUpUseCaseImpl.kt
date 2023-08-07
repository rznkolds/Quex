package com.rznkolds.domain.usecase.account

import android.net.Uri
import com.rznkolds.data.dto.Status
import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
    private val mapper: BaseMapper<Status, StatusUI>
) : SignUpUseCase {

    override operator fun invoke(
        name: String,
        description: String,
        email: String,
        password: String,
        picture: Uri
    ): Flow<Resource<StatusUI>> = flow {

        try {
            emit(Resource.Loading)

            emit(
                Resource.Success(
                    mapper.map(
                        repository.register(
                            name,
                            description,
                            email,
                            password,
                            picture
                        )
                    )
                )
            )

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}