package com.rznkolds.domain.mapper.list

import com.rznkolds.data.dto.Favorite
import com.rznkolds.domain.model.FavoriteUI
import javax.inject.Inject

class FavoritesMapperImpl @Inject constructor() : BaseListMapper<Favorite, FavoriteUI> {

    override fun map(input: List<Favorite>): List<FavoriteUI> {
        return input.map {
            FavoriteUI(
                uid = it.uid ?: "",
                coin = it.coin ?: "",
            )
        } ?: emptyList()
    }
}