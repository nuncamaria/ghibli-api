package com.nuncamaria.films.domain

import com.nuncamaria.films.data.repository.FilmsRepository
import com.nuncamaria.films.domain.model.FilmModel
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(private val repository: FilmsRepository) {

    suspend operator fun invoke(): Result<List<FilmModel>> =
        repository.getFilms().map { films ->
            films.map { it.toModel() }
        }
}