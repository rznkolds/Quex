package com.rk.quex.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val uid: String?,
    val name: String?,
    val profile: String?,
    val coin: String?,
    val comment: String?,
    val date: Int?,
    val time: Int?
): Parcelable
