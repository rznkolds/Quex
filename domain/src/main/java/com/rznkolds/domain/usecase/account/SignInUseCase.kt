package com.rznkolds.domain.usecase.account

import com.rznkolds.domain.common.Resource
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {

    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>>
}