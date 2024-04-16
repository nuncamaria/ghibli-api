package com.nuncamaria.films.data

object ApiConstants {
    internal const val BASE_URL = "https://ghibliapi.vercel.app"

    const val FILMS = "/films"
    const val FILM_DETAIL = "/films/{id}"
    const val PEOPLE = "/people"
    const val LOCATIONS = "/locations"
    const val SPECIES = "/species"
    const val VEHICLES = "/vehicles"

    internal fun getUrl(endpoint: String): String = "${BASE_URL}${endpoint}"
}