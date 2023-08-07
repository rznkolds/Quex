package com.rznkolds.domain.di

import com.rznkolds.data.source.remote.RemoteDataSource
import com.rznkolds.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseAuthenticator(authenticator: RemoteDataSourceImpl): RemoteDataSource
}