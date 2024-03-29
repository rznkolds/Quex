package com.rznkolds.data.dto

import com.google.gson.annotations.SerializedName

data class Coin(
    val name: String?,
    @SerializedName("image")
    val picture: String?,
    @SerializedName("current_price")
    val price: String?
)