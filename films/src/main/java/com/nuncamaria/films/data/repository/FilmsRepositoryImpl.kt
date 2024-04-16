package com.nuncamaria.films.data.repository

import com.nuncamaria.films.data.datasource.FilmsCloudDataSource
import com.nuncamaria.films.data.response.FilmResponse
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(private val dataSource: FilmsCloudDataSource) : FilmsRepository {

    override suspend fun getFilms(): Result<List<FilmResponse>> =
        dataSource.getFilms()
}