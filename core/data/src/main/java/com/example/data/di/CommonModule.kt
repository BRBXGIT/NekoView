package com.example.data.di

import android.content.Context
import com.example.data.domain.CommonRepo
import com.example.data.local.data_store.NekoViewDataStore
import com.example.data.remote.ktor.CommonKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.CommonRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun provideCommonKtorClient(): CommonKtorClient {
        return CommonKtorClient(
            createHttpClient()
        )
    }

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): NekoViewDataStore {
        return NekoViewDataStore(context)
    }

    @Provides
    @Singleton
    fun provideCommonRepo(
        commonKtorClient: CommonKtorClient,
        dataStore: NekoViewDataStore
    ): CommonRepo {
        return CommonRepoImpl(commonKtorClient, dataStore)
    }
}