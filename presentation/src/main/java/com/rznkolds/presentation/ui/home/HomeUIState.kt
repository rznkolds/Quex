package com.rznkolds.presentation.ui.home

import android.net.Uri
import com.rznkolds.domain.model.CoinUI

data class HomeUIState(
    val coins: List<CoinUI>? = null,
    val picture: Uri? = null,
    val loading: String? = null,
    val error: String? = null,
)
