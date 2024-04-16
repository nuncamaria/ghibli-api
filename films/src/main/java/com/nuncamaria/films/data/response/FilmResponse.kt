package com.nuncamaria.films.data.response

import com.nuncamaria.films.domain.model.FilmModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmResponse(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val director: String = "",
    val image: String = "",
    @SerialName("movie_banner")
    val movieBanner: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("rt_score")
    val rtScore: String = "",
    val people: List<String> = listOf(),
    val species: List<String> = listOf(),
    val locations: List<String> = listOf(),
    val vehicles: List<String> = listOf(),
    val url: String = "",
) {
    fun toModel(): FilmModel =
        FilmModel(
            id,
            title,
            image,
            movieBanner,
            description,
            director,
            releaseDate,
            rtScore
        )
}
