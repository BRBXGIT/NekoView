package com.example.data.di

import com.example.data.domain.SearchScreenRepo
import com.example.data.remote.ktor.SearchScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.SearchScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchScreenModule {

    @Provides
    @Singleton
    fun provideSearchScreenKtorClient(): SearchScreenKtorClient {
        return SearchScreenKtorClient(
            createHttpClient()
        )
    }

    @Provides
    @Singleton
    fun provideSearchScreenRepo(ktorClient: SearchScreenKtorClient): SearchScreenRepo {
        return SearchScreenRepoImpl(ktorClient)
    }
}