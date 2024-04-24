package com.nuncamaria.people.di

import com.nuncamaria.network.NetworkService
import com.nuncamaria.people.data.datasource.PeopleCloudDataSource
import com.nuncamaria.people.data.repository.PeopleRepository
import com.nuncamaria.people.data.repository.PeopleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PeopleModule {

    @Provides
    @Singleton // This is to create ONLY one instance
    fun providesNetworkService() = NetworkService

    @Provides
    @Singleton // This is to create ONLY one instance
    fun providesPeopleData() = PeopleCloudDataSource(providesNetworkService())

    @Provides
    @Singleton
    fun providesPeopleRepository(dataSource: PeopleCloudDataSource): PeopleRepository =
        PeopleRepositoryImpl(dataSource)
}