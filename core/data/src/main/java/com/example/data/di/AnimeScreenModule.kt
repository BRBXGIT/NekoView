package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.domain.AnimeScreenRepo
import com.example.data.local.watched_eps_db.TitleWatchedEpsDao
import com.example.data.local.watched_eps_db.TitleWatchedEpsDb
import com.example.data.remote.ktor.AnimeScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.AnimeScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideTitleWatchedEpsDao(
        @ApplicationContext context: Context
    ): TitleWatchedEpsDao {
        return Room.databaseBuilder(
            context = context,
            klass = TitleWatchedEpsDb::class.java,
            name = "title_watched_eps_db"
        ).build().titleWatchedEpsDao()
    }

    @Provides
    @Singleton
    fun provideAnimeScreenRepo(
        ktorClient: AnimeScreenKtorClient,
        watchedEpsDao: TitleWatchedEpsDao
    ): AnimeScreenRepo {
        return AnimeScreenRepoImpl(ktorClient, watchedEpsDao)
    }
}