package com.rk.quex.data.model

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("success")
    val received: Boolean
)
