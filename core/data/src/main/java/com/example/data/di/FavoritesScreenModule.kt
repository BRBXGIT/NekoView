package com.example.data.di

import com.example.data.domain.FavoritesScreenRepo
import com.example.data.remote.ktor.FavoritesScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.FavoritesScreenRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import io.ktor.client.engine.okhttp.OkHttp


@Module
@InstallIn(SingletonComponent::class)
object FavoritesScreenModule {

    @Provides
    @Singleton
    fun provideFavoritesScreenKtorClient(): FavoritesScreenKtorClient {
        return FavoritesScreenKtorClient(
            createHttpClient(
                OkHttp.create()
            )
        )
    }

    @Provides
    @Singleton
    fun provideFavoritesScreenRepo(
        favoritesScreenKtorClient: FavoritesScreenKtorClient
    ): FavoritesScreenRepo {
        return FavoritesScreenRepoImpl(favoritesScreenKtorClient)
    }
}