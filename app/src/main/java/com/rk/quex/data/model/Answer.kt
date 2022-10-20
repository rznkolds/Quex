package com.rk.quex.data.model

data class Answer(

    val uid: String,
    val name: String,
    val above_uid: String,
    val url: String,
    val coin: String,
    val comment: String,
    val top_date: Int,
    val top_time: Int
)
