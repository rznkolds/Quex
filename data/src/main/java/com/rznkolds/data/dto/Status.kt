package com.rznkolds.data.dto

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("success")
    val received: Boolean
)
