package com.nuncamaria.films.data.datasource

import com.nuncamaria.films.data.ApiConstants
import com.nuncamaria.films.data.ApiConstants.getUrl
import com.nuncamaria.films.data.response.FilmResponse
import com.nuncamaria.network.HttpMethodRequest
import com.nuncamaria.network.NetworkService
import javax.inject.Inject

class FilmsCloudDataSource @Inject constructor(private val networkService: NetworkService) {

    suspend fun getFilms(): Result<List<FilmResponse>> =
        networkService.createApi<List<FilmResponse>>(
            url = getUrl(ApiConstants.FILMS),
            httpMethod = HttpMethodRequest.GET,
            parameters = linkedMapOf(
                "fields" to "",
                "limit" to null
            )
        )
}