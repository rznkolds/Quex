package com.rznkolds.domain.usecase.user

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.FavoriteUI
import com.rznkolds.domain.model.UserUI
import kotlinx.coroutines.flow.Flow

interface GetUserUseCase {

    operator fun invoke(uid:String): Flow<Resource<UserUI>>
}