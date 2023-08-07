package com.rznkolds.data.source.remote

import android.net.Uri
import com.rznkolds.data.api.CoinService
import com.rznkolds.data.api.UserService
import com.rznkolds.data.dto.Answer
import com.rznkolds.data.dto.Coin
import com.rznkolds.data.dto.Comment
import com.rznkolds.data.dto.Favorite
import com.rznkolds.data.dto.Notification
import com.rznkolds.data.dto.Status
import com.rznkolds.data.dto.User
import com.rznkolds.data.repository.authenticator.Authenticator
import java.util.Calendar
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val userService: UserService,
    private val coinService: CoinService,
    private val authenticator: Authenticator
) : RemoteDataSource {

    private val calendar by lazy { Calendar.getInstance() }

    override suspend fun register(
        name: String,
        description: String,
        email: String,
        password: String,
        picture: Uri
    ): Status {

        authenticator.register(email, password)

        val user = User(
            authenticator.getUid(),
            name,
            description,
            authenticator.setPicture(
                picture
            )
        )

        return userService.postUser(user)
    }

    override suspend fun login(email: String, password: String): Boolean {

        return authenticator.login(email, password)
    }

    override suspend fun getPicture(): Uri {

        return authenticator.getPicture()
    }

    override suspend fun getProfile(uid: String): User {

        return userService.getUser(uid)
    }

    override suspend fun signOut() = authenticator.signOut()

    override suspend fun getCoins(): ArrayList<Coin> {

        return coinService.getCoins()
    }

    override suspend fun getFavorites(uid: String): ArrayList<Favorite> {

        return userService.getFavoriteCoins(uid)
    }

    override suspend fun getNotifications(): ArrayList<Notification> {

        return userService.getNotifications(authenticator.getUid())
    }

    override suspend fun deleteNotifications(): Status {

        return userService.deleteNotifications(authenticator.getUid())
    }

    override suspend fun getComments(coin: String) = userService.getComments(coin)

    override suspend fun postComment(coin: String, text: String): Status {

        return userService.postComment(
            Comment(
                authenticator.getUid(),
                "",
                "",
                coin,
                text,
                date(),
                time()
            )
        )
    }

    override suspend fun updateComment(
        text: String,
        comment: Comment
    ): Status = userService.updateComment(
        Comment(
            comment.uid,
            "",
            "",
            comment.coin,
            text,
            comment.date,
            comment.time
        )
    )

    override suspend fun deleteComment(comment: Comment): Status {

        return userService.deleteComment(comment)
    }

    override suspend fun getAnswers(
        coin: String,
        uid: String,
        date: Int,
        time: Int
    ): ArrayList<Answer> = userService.getAnswers(coin, uid, date, time)

    override suspend fun postAnswer(
        uid: String,
        coin: String,
        text: String,
        date: Int,
        time: Int
    ): Status = userService.postAnswer(
        Answer(
            authenticator.getUid(),
            "",
            uid,
            "",
            coin,
            text,
            date,
            time
        )
    )

    override suspend fun updateAnswer(text: String, answer: Answer): Status {

        return userService.updateAnswer(
            Answer(
                answer.uid,
                "",
                answer.top,
                "",
                answer.coin,
                text,
                answer.date,
                answer.time
            )
        )
    }

    override suspend fun deleteAnswer(answer: Answer): Status {

        return userService.deleteAnswer(answer)
    }

    private fun date(): Int {

        val date = calendar[Calendar.DAY_OF_MONTH].toString() +
                calendar[Calendar.MONTH].toString() +
                calendar[Calendar.YEAR].toString()

        return date.toInt()
    }

    private fun time(): Int {

        val time = calendar[Calendar.MILLISECOND].toString() +
                calendar[Calendar.MINUTE].toString() +
                calendar[Calendar.HOUR_OF_DAY].toString()

        return time.toInt()
    }
}