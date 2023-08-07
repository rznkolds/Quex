package com.rznkolds.presentation.ui.signin

data class SignInUIState(
    val logged: Boolean? = null,
    val fail: String? = null,
    val loading: String? = null,
    val error: String? = null,
)
