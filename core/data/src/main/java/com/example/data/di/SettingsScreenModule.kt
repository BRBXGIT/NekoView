package com.example.data.di

import com.example.data.domain.SettingsScreenRepo
import com.example.data.remote.ktor.SettingsScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.SettingsScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsScreenModule {

    @Provides
    @Singleton
    fun provideSettingsScreenKtorClient(): SettingsScreenKtorClient {
        return SettingsScreenKtorClient(
            createHttpClient(
                OkHttp.create()
            )
        )
    }

    @Provides
    @Singleton
    fun provideSettingsScreenRepo(ktorClient: SettingsScreenKtorClient): SettingsScreenRepo {
        return SettingsScreenRepoImpl(ktorClient)
    }
}