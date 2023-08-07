package com.rznkolds.domain.usecase.account

import android.net.Uri
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.StatusUI
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {

    operator fun invoke(
        name: String,
        description: String,
        email: String,
        password: String,
        picture: Uri
    ): Flow<Resource<StatusUI>>
}