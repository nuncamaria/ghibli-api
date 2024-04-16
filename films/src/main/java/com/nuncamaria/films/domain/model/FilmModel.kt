package com.nuncamaria.films.domain.model

data class FilmModel(
    val id: String = "",
    val title: String = "",
    val image: String = "",
    val movieBanner: String = "",
    val description: String = "",
    val director: String = "",
    val releaseDate: String = "",
    val rtScore: String = ""
)