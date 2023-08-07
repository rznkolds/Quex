package com.rznkolds.presentation.ui.profile

import com.rznkolds.domain.model.FavoriteUI
import com.rznkolds.domain.model.UserUI

data class ProfileUIState(
    val user: UserUI? = null,
    val favorites: List<FavoriteUI>? = null,
    val loading: String? = null,
    val error: String? = null,
)
