package com.rznkolds.domain.di

import com.rznkolds.data.dto.Answer
import com.rznkolds.data.dto.Coin
import com.rznkolds.data.dto.Comment
import com.rznkolds.data.dto.Favorite
import com.rznkolds.data.dto.Notification
import com.rznkolds.data.dto.Status
import com.rznkolds.data.dto.User
import com.rznkolds.domain.mapper.list.AnswersMapperImpl
import com.rznkolds.domain.mapper.list.BaseListMapper
import com.rznkolds.domain.mapper.list.CoinsMapperImpl
import com.rznkolds.domain.mapper.list.CommentsMapperImpl
import com.rznkolds.domain.mapper.list.FavoritesMapperImpl
import com.rznkolds.domain.mapper.list.NotificationsMapperImpl
import com.rznkolds.domain.mapper.model.ui.AnswerUIMapperImpl
import com.rznkolds.domain.mapper.model.BaseMapper
import com.rznkolds.domain.mapper.model.dto.StatusMapperImpl
import com.rznkolds.domain.mapper.model.ui.CommentUIMapperImpl
import com.rznkolds.domain.mapper.model.dto.UserMapperImpl
import com.rznkolds.domain.model.AnswerUI
import com.rznkolds.domain.model.CoinUI
import com.rznkolds.domain.model.CommentUI
import com.rznkolds.domain.model.FavoriteUI
import com.rznkolds.domain.model.NotificationUI
import com.rznkolds.domain.model.StatusUI
import com.rznkolds.domain.model.UserUI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    // DATA LAYER LIST MAPPER

    @Binds
    @Singleton
    abstract fun bindAnswersMapper(answersMapper: AnswersMapperImpl): BaseListMapper<Answer, AnswerUI>

    @Binds
    @Singleton
    abstract fun bindCoinsMapper(coinsMapper: CoinsMapperImpl): BaseListMapper<Coin, CoinUI>

    @Binds
    @Singleton
    abstract fun bindCommentsMapper(commentsMapper: CommentsMapperImpl): BaseListMapper<Comment, CommentUI>

    @Binds
    @Singleton
    abstract fun bindFavoritesMapper(favoritesMapper: FavoritesMapperImpl): BaseListMapper<Favorite, FavoriteUI>

    @Binds
    @Singleton
    abstract fun bindNotificationsMapper(notificationsMapper: NotificationsMapperImpl): BaseListMapper<Notification, NotificationUI>

    // DATA LAYER MODEL MAPPER

    @Binds
    @Singleton
    abstract fun bindStatusMapper(userMapper: StatusMapperImpl): BaseMapper<Status, StatusUI>

    @Binds
    @Singleton
    abstract fun bindUserMapper(userMapper: UserMapperImpl): BaseMapper<User, UserUI>

    // DOMAIN LAYER MODEL MAPPER

    @Binds
    @Singleton
    abstract fun bindAnswerMapper(answerMapper: AnswerUIMapperImpl): BaseMapper<AnswerUI, Answer>

    @Binds
    @Singleton
    abstract fun bindCommentMapper(commentMapper: CommentUIMapperImpl): BaseMapper<CommentUI, Comment>
}