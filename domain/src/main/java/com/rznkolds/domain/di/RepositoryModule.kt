package com.rznkolds.domain.di

import com.rznkolds.data.repository.coin.CoinRepository
import com.rznkolds.data.repository.coin.CoinRepositoryImpl
import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoinRepository(repo: CoinRepositoryImpl): CoinRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(repo: UserRepositoryImpl): UserRepository
}