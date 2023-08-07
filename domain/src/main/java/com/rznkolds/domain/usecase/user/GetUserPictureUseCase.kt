package com.rznkolds.domain.usecase.user

import android.net.Uri
import com.rznkolds.domain.common.Resource
import kotlinx.coroutines.flow.Flow

interface GetUserPictureUseCase {

    operator fun invoke(): Flow<Resource<Uri>>
}