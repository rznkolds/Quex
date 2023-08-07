package com.rznkolds.domain.di

import com.google.gson.GsonBuilder
import com.rznkolds.data.api.CoinService
import com.rznkolds.data.api.UserService
import com.rznkolds.data.common.Constants.BASE_COIN_URL
import com.rznkolds.data.common.Constants.BASE_USER_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideInstance(url: String): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(url)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserService(): UserService = provideInstance(BASE_USER_URL).create(UserService::class.java)

    @Provides
    @Singleton
    fun provideCoinService(): CoinService = provideInstance(BASE_COIN_URL).create(CoinService::class.java)
}