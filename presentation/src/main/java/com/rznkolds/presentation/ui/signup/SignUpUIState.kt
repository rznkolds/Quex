package com.rznkolds.presentation.ui.signup

data class SignUpUIState(
    val registered: Boolean? = null,
    val fail: String? = null,
    val loading: String? = null,
    val error: String? = null,
)