package com.example.data.di

import com.example.data.domain.HomeScreenRepo
import com.example.data.remote.ktor.HomeScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.HomeScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeScreenModule {

    @Provides
    @Singleton
    fun provideHomeScreenKtorClient(): HomeScreenKtorClient {
        return HomeScreenKtorClient(
            createHttpClient()
        )
    }

    @Provides
    @Singleton
    fun provideHomeScreenRepo(
        homeScreenKtorClient: HomeScreenKtorClient
    ): HomeScreenRepo {
        return HomeScreenRepoImpl(homeScreenKtorClient)
    }
}