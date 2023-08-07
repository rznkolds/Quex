package com.rznkolds.data.repository.coin

import com.rznkolds.data.dto.Answer
import com.rznkolds.data.dto.Coin
import com.rznkolds.data.dto.Comment
import com.rznkolds.data.dto.Favorite
import com.rznkolds.data.dto.Notification
import com.rznkolds.data.dto.Status

interface CoinRepository {

    suspend fun getCoins(): ArrayList<Coin>

    suspend fun getFavorites(uid: String): ArrayList<Favorite>

    suspend fun getNotifications(): ArrayList<Notification>

    suspend fun deleteNotifications(): Status

    // Saving and receiving comments

    suspend fun getComments(
        coin: String
    ): ArrayList<Comment>

    suspend fun postComment(
        coin: String,
        text: String
    ): Status

    suspend fun updateComment(
        text: String,
        comment: Comment
    ): Status

    suspend fun deleteComment(comment: Comment):Status

    // Saving and receiving answers

    suspend fun getAnswers(
        coin: String,
        uid: String,
        date: Int,
        time: Int
    ): ArrayList<Answer>

    suspend fun postAnswer(
        uid: String,
        coin: String,
        text: String,
        date: Int,
        time: Int
    ): Status

    suspend fun updateAnswer(
        text: String,
        answer: Answer
    ): Status

    suspend fun deleteAnswer(answer: Answer): Status
}