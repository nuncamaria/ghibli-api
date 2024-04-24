package com.nuncamaria.locations.data.repository

import com.nuncamaria.locations.data.datasource.LocationsCloudDataSource
import com.nuncamaria.locations.data.response.LocationResponse
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(private val dataSource: LocationsCloudDataSource) : LocationsRepository {

    override suspend fun getLocations(): Result<List<LocationResponse>> =
        dataSource.getLocations()
}