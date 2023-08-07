package com.rznkolds.domain.di

import com.rznkolds.domain.usecase.account.SignInUseCase
import com.rznkolds.domain.usecase.account.SignInUseCaseImpl
import com.rznkolds.domain.usecase.account.SignOutUseCase
import com.rznkolds.domain.usecase.account.SignOutUseCaseImpl
import com.rznkolds.domain.usecase.account.SignUpUseCase
import com.rznkolds.domain.usecase.account.SignUpUseCaseImpl
import com.rznkolds.domain.usecase.answer.AddAnswerUseCase
import com.rznkolds.domain.usecase.answer.AddAnswerUseCaseImpl
import com.rznkolds.domain.usecase.answer.DeleteAnswerUseCase
import com.rznkolds.domain.usecase.answer.DeleteAnswerUseCaseImpl
import com.rznkolds.domain.usecase.answer.GetAnswersUseCase
import com.rznkolds.domain.usecase.answer.GetAnswersUseCaseImpl
import com.rznkolds.domain.usecase.answer.UpdateAnswerUseCase
import com.rznkolds.domain.usecase.answer.UpdateAnswerUseCaseImpl
import com.rznkolds.domain.usecase.coin.GetCoinsUseCase
import com.rznkolds.domain.usecase.coin.GetCoinsUseCaseImpl
import com.rznkolds.domain.usecase.comment.AddCommentUseCase
import com.rznkolds.domain.usecase.comment.AddCommentUseCaseImpl
import com.rznkolds.domain.usecase.comment.DeleteCommentUseCase
import com.rznkolds.domain.usecase.comment.DeleteCommentUseCaseImpl
import com.rznkolds.domain.usecase.comment.GetCommentsUseCase
import com.rznkolds.domain.usecase.comment.GetCommentsUseCaseImpl
import com.rznkolds.domain.usecase.comment.UpdateCommentUseCase
import com.rznkolds.domain.usecase.comment.UpdateCommentUseCaseImpl
import com.rznkolds.domain.usecase.favorite.GetFavoritesUseCase
import com.rznkolds.domain.usecase.favorite.GetFavoritesUseCaseImpl
import com.rznkolds.domain.usecase.notification.DeleteNotificationUseCase
import com.rznkolds.domain.usecase.notification.DeleteNotificationUseCaseImpl
import com.rznkolds.domain.usecase.notification.GetNotificationsUseCase
import com.rznkolds.domain.usecase.notification.GetNotificationsUseCaseImpl
import com.rznkolds.domain.usecase.user.GetUserPictureUseCase
import com.rznkolds.domain.usecase.user.GetUserPictureUseCaseImpl
import com.rznkolds.domain.usecase.user.GetUserUseCase
import com.rznkolds.domain.usecase.user.GetUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    // ACCOUNT OPERATIONS

    @Binds
    @Singleton
    abstract fun bindSignInUseCase(usecase: SignInUseCaseImpl): SignInUseCase

    @Binds
    @Singleton
    abstract fun bindSignOutUseCase(usecase: SignOutUseCaseImpl): SignOutUseCase

    @Binds
    @Singleton
    abstract fun bindSignUpUseCase(usecase: SignUpUseCaseImpl): SignUpUseCase

    // ANSWER OPERATIONS

    @Binds
    @Singleton
    abstract fun bindAddAnswerUseCase(usecase: AddAnswerUseCaseImpl): AddAnswerUseCase

    @Binds
    @Singleton
    abstract fun bindDeleteAnswerUseCase(usecase: DeleteAnswerUseCaseImpl): DeleteAnswerUseCase

    @Binds
    @Singleton
    abstract fun bindGetAnswersUseCase(usecase: GetAnswersUseCaseImpl): GetAnswersUseCase

    @Binds
    @Singleton
    abstract fun bindUpdateAnswerUseCase(usecase: UpdateAnswerUseCaseImpl): UpdateAnswerUseCase

    // COIN OPERATIONS

    @Binds
    @Singleton
    abstract fun bindGetCoinsUseCase(usecase: GetCoinsUseCaseImpl): GetCoinsUseCase

    // COMMENT OPERATIONS

    @Binds
    @Singleton
    abstract fun bindAddCommentUseCase(usecase: AddCommentUseCaseImpl): AddCommentUseCase

    @Binds
    @Singleton
    abstract fun bindDeleteCommentUseCase(usecase: DeleteCommentUseCaseImpl): DeleteCommentUseCase

    @Binds
    @Singleton
    abstract fun bindGetCommentsUseCase(usecase: GetCommentsUseCaseImpl): GetCommentsUseCase

    @Binds
    @Singleton
    abstract fun bindUpdateCommentUseCase(usecase: UpdateCommentUseCaseImpl): UpdateCommentUseCase

    // FAVORITE OPERATIONS

    @Binds
    @Singleton
    abstract fun bindGetFavoritesUseCase(usecase: GetFavoritesUseCaseImpl): GetFavoritesUseCase

    // NOTIFICATION OPERATIONS

    @Binds
    @Singleton
    abstract fun bindDeleteNotificationUseCase(usecase: DeleteNotificationUseCaseImpl): DeleteNotificationUseCase

    @Binds
    @Singleton
    abstract fun bindGetNotificationsUseCase(usecase: GetNotificationsUseCaseImpl): GetNotificationsUseCase

    // USER OPERATIONS

    @Binds
    @Singleton
    abstract fun bindGetUserPictureUseCase(usecase: GetUserPictureUseCaseImpl): GetUserPictureUseCase

    @Binds
    @Singleton
    abstract fun bindGetUserUseCase(usecase: GetUserUseCaseImpl): GetUserUseCase
}