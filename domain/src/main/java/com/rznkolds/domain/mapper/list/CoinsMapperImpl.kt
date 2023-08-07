package com.rznkolds.domain.mapper.list

import com.rznkolds.data.dto.Coin
import com.rznkolds.domain.model.CoinUI
import javax.inject.Inject

class CoinsMapperImpl @Inject constructor() : BaseListMapper<Coin, CoinUI> {

    override fun map(input: List<Coin>): List<CoinUI> {
        return input.map {
            CoinUI(
                name = it.name ?: "",
                picture = it.picture ?: "",
                price = it.price ?: "",
            )
        } ?: emptyList()
    }
}