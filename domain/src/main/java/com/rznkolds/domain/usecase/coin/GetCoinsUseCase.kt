package com.rznkolds.domain.usecase.coin

import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.CoinUI
import kotlinx.coroutines.flow.Flow

interface GetCoinsUseCase {

    operator fun invoke(): Flow<Resource<List<CoinUI>>>
}