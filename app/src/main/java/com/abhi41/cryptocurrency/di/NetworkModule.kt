package com.abhi41.cryptocurrency.di

import com.abhi41.cryptocurrency.common.Constants
import com.abhi41.cryptocurrency.data.remote.CoinPaprikaApi
import com.abhi41.cryptocurrency.data.repository.CoinRepositoryImpl
import com.abhi41.cryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverter: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providePaprikaApi(
        retrofit: Retrofit,
    ): CoinPaprikaApi {
        return retrofit.create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository{
        return CoinRepositoryImpl(api)
    }

}