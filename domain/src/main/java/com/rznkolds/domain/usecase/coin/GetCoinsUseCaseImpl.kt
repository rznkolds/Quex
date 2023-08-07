package com.rznkolds.domain.usecase.coin

import com.rznkolds.data.dto.Coin
import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.list.BaseListMapper
import com.rznkolds.domain.model.CoinUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCaseImpl @Inject constructor(
    private val repository: CoinRepository,
    private val mapper: BaseListMapper<Coin,CoinUI>
) : GetCoinsUseCase {

    override operator fun invoke(): Flow<Resource<List<CoinUI>>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repository.getCoins())))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }

    }.flowOn(Dispatchers.IO)
}