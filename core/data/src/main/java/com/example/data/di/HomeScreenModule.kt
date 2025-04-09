package com.example.data.di

import com.example.data.domain.HomeScreenRepo
import com.example.data.domain.HomeScreenRepoImpl
import com.example.data.remote.ktor.HomeScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import io.ktor.client.engine.okhttp.OkHttp

@Module
@InstallIn(SingletonComponent::class)
object HomeScreenModule {

    @Provides
    @Singleton
    fun provideHomeScreenKtorClient(): HomeScreenKtorClient {
        return HomeScreenKtorClient(
            createHttpClient(
                OkHttp.create()
            )
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