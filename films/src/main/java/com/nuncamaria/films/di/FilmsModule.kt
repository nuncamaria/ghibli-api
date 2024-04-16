package com.nuncamaria.films.di

import com.nuncamaria.films.data.datasource.FilmsCloudDataSource
import com.nuncamaria.films.data.repository.FilmsRepository
import com.nuncamaria.films.data.repository.FilmsRepositoryImpl
import com.nuncamaria.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilmsModule {

    @Provides
    @Singleton // This is to create ONLY one instance
    fun providesNetworkService() = NetworkService

    @Provides
    @Singleton // This is to create ONLY one instance
    fun providesFilmsData() = FilmsCloudDataSource(providesNetworkService())

    @Provides
    @Singleton
    fun providesFilmsRepository(dataSource: FilmsCloudDataSource): FilmsRepository =
        FilmsRepositoryImpl(dataSource)
}