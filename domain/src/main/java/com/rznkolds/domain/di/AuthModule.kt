package com.rznkolds.domain.di

import com.rznkolds.data.repository.authenticator.Authenticator
import com.rznkolds.data.repository.authenticator.AuthenticatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseAuthenticator(authenticator: AuthenticatorImpl): Authenticator
}