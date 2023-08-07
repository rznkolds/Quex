package com.rznkolds.data.source.remote

import android.net.Uri
import com.rznkolds.data.dto.Answer
import com.rznkolds.data.dto.Coin
import com.rznkolds.data.dto.Comment
import com.rznkolds.data.dto.Favorite
import com.rznkolds.data.dto.Notification
import com.rznkolds.data.dto.Status
import com.rznkolds.data.dto.User

interface RemoteDataSource {

    suspend fun login(email: String, password: String): Boolean

    suspend fun register(
        name: String,
        description: String,
        email: String,
        password: String,
        picture: Uri
    ): Status

    suspend fun getPicture(): Uri

    suspend fun getProfile(uid: String): User

    suspend fun signOut()

    suspend fun getCoins(): ArrayList<Coin>

    suspend fun getFavorites(uid: String): ArrayList<Favorite>

    suspend fun getNotifications(): ArrayList<Notification>

    suspend fun deleteNotifications(): Status

    suspend fun getComments(coin: String): ArrayList<Comment>

    suspend fun postComment(coin: String, text: String): Status

    suspend fun updateComment(text: String, comment: Comment): Status

    suspend fun deleteComment(comment: Comment): Status

    suspend fun getAnswers(coin: String, uid: String, date: Int, time: Int): ArrayList<Answer>

    suspend fun postAnswer(uid: String, coin: String, text: String, date: Int, time: Int): Status

    suspend fun updateAnswer(text: String, answer: Answer): Status

    suspend fun deleteAnswer(answer: Answer): Status
}