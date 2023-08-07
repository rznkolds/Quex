package com.rznkolds.domain.usecase.favorite

import com.rznkolds.data.dto.Favorite
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.list.BaseListMapper
import com.rznkolds.domain.model.FavoriteUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: BaseListMapper<Favorite, FavoriteUI>
) : GetFavoritesUseCase {

    override operator fun invoke(uid:String): Flow<Resource<List<FavoriteUI>>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repository.getFavorites(uid))))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}