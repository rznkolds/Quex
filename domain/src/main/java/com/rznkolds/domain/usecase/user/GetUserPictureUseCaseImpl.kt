package com.rznkolds.domain.usecase.user

import android.net.Uri
import com.rznkolds.data.dto.Status
import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetUserPictureUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUserPictureUseCase {

    override operator fun invoke(): Flow<Resource<Uri>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(repository.getPicture()))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}