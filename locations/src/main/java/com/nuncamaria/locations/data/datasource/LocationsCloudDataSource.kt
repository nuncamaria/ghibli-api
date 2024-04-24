package com.nuncamaria.locations.data.datasource

import com.nuncamaria.locations.data.ApiConstants
import com.nuncamaria.locations.data.ApiConstants.getUrl
import com.nuncamaria.locations.data.response.LocationResponse
import com.nuncamaria.network.HttpMethodRequest
import com.nuncamaria.network.NetworkService
import javax.inject.Inject

class LocationsCloudDataSource @Inject constructor(private val networkService: NetworkService) {

    suspend fun getLocations(): Result<List<LocationResponse>> =
        networkService.createApi<List<LocationResponse>>(
            url = getUrl(ApiConstants.LOCATIONS),
            httpMethod = HttpMethodRequest.GET,
            parameters = linkedMapOf(
                "fields" to "",
                "limit" to null
            )
        )
}