package com.rznkolds.presentation.ui.notification

import com.rznkolds.domain.model.NotificationUI

data class NotificationUIState(
    val notifications: List<NotificationUI>? = null,
    val deleted: Boolean? = null,
    val loading: String? = null,
    val error: String? = null,
)
