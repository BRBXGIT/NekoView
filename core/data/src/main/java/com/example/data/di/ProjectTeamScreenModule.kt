package com.example.data.di

import com.example.data.domain.ProjectTeamScreenRepo
import com.example.data.remote.ktor.HomeScreenKtorClient
import com.example.data.remote.ktor.ProjectTeamScreenKtorClient
import com.example.data.remote.ktor.createHttpClient
import com.example.data.repositories.ProjectTeamRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProjectTeamScreenModule {

    @Provides
    @Singleton
    fun provideProjectTeamKtorClient(): ProjectTeamScreenKtorClient {
        return ProjectTeamScreenKtorClient(
            createHttpClient()
        )
    }

    @Provides
    @Singleton
    fun provideProjectTeamScreenRepo(ktorClient: ProjectTeamScreenKtorClient): ProjectTeamScreenRepo {
        return ProjectTeamRepoImpl(ktorClient)
    }
}