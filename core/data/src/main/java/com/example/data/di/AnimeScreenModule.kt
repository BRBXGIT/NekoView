package com.example.data.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.data.domain.AnimeScreenRepo
import com.example.data.remote.ktor.AnimeScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.AnimeScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeScreenModule {

    @Provides
    @Singleton
    fun provideAnimeScreenKtorClient(): AnimeScreenKtorClient {
        return AnimeScreenKtorClient(
            createHttpClient()
        )
    }

    @Provides
    @Singleton
    fun provideAnimeScreenRepo(ktorClient: AnimeScreenKtorClient): AnimeScreenRepo {
        return AnimeScreenRepoImpl(ktorClient)
    }
}