package com.rznkolds.domain.usecase.favorite

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.FavoriteUI
import kotlinx.coroutines.flow.Flow

interface GetFavoritesUseCase {

    operator fun invoke(uid:String): Flow<Resource<List<FavoriteUI>>>
}