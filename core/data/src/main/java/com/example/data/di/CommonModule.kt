package com.example.data.di

import android.content.Context
import com.example.data.domain.CommonRepo
import com.example.data.repositories.CommonRepoImpl
import com.example.data.local.data_store.NekoViewDataStore
import com.example.data.remote.ktor.CommonKtorClient
import com.example.data.remote.ktor.createHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    @Singleton
    fun provideCommonKtorClient(): CommonKtorClient {
        return CommonKtorClient(
            createHttpClient(
                OkHttp.create()
            )
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