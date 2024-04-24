package com.nuncamaria.locations.data.repository

import com.nuncamaria.locations.data.response.LocationResponse

interface LocationsRepository {

    suspend fun getLocations(): Result<List<LocationResponse>>
}