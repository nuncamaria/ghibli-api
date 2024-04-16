package com.nuncamaria.films.data.repository

import com.nuncamaria.films.data.response.FilmResponse

interface FilmsRepository {

    suspend fun getFilms(): Result<List<FilmResponse>>
}