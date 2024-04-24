package com.nuncamaria.locations.di

import com.nuncamaria.locations.data.datasource.LocationsCloudDataSource
import com.nuncamaria.locations.data.repository.LocationsRepository
import com.nuncamaria.locations.data.repository.LocationsRepositoryImpl
import com.nuncamaria.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationsModule {

    @Provides
    @Singleton // This is to create ONLY one instance
    fun providesNetworkService() = NetworkService

    @Provides
    @Singleton // This is to create ONLY one instance
    fun providesLocationsData() = LocationsCloudDataSource(providesNetworkService())

    @Provides
    @Singleton
    fun providesLocationsRepository(dataSource: LocationsCloudDataSource): LocationsRepository =
        LocationsRepositoryImpl(dataSource)
}