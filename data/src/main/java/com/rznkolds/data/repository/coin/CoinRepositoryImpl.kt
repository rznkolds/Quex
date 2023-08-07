package com.rznkolds.data.repository.coin

import com.rznkolds.data.dto.Answer
import com.rznkolds.data.dto.Comment
import com.rznkolds.data.source.remote.RemoteDataSource
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val source: RemoteDataSource
) : CoinRepository {

    override suspend fun getCoins() = source.getCoins()

    override suspend fun getFavorites(uid: String) = source.getFavorites(uid)

    override suspend fun getNotifications() = source.getNotifications()

    override suspend fun deleteNotifications() = source.deleteNotifications()

    override suspend fun getComments(coin: String) = source.getComments(coin)

    override suspend fun postComment(coin: String, text: String) = source.postComment(coin, text)

    override suspend fun updateComment(text: String, comment: Comment) = source.updateComment(text, comment)

    override suspend fun deleteComment(comment: Comment) = source.deleteComment(comment)

    override suspend fun getAnswers(
        coin: String,
        uid: String,
        date: Int,
        time: Int
    ) = source.getAnswers(coin, uid, date, time)

    override suspend fun postAnswer(
        uid: String,
        coin: String,
        text: String,
        date: Int,
        time: Int
    ) = source.postAnswer(uid, coin, text, date, time)

    override suspend fun updateAnswer(text: String, answer: Answer) = source.updateAnswer(text, answer)

    override suspend fun deleteAnswer(answer: Answer) = source.deleteAnswer(answer)
}