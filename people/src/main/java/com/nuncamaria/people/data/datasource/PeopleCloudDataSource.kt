package com.nuncamaria.people.data.datasource

import com.nuncamaria.people.data.ApiConstants
import com.nuncamaria.people.data.ApiConstants.getUrl
import com.nuncamaria.people.data.response.PersonResponse
import com.nuncamaria.network.HttpMethodRequest
import com.nuncamaria.network.NetworkService
import javax.inject.Inject

class PeopleCloudDataSource @Inject constructor(private val networkService: NetworkService) {

    suspend fun getPeople(): Result<List<PersonResponse>> =
        networkService.createApi<List<PersonResponse>>(
            url = getUrl(ApiConstants.PEOPLE),
            httpMethod = HttpMethodRequest.GET,
            parameters = linkedMapOf(
                "fields" to "",
                "limit" to null
            )
        )
}