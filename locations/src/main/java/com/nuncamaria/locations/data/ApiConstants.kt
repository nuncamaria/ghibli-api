package com.nuncamaria.locations.data

object ApiConstants {
    internal const val BASE_URL = "https://ghibliapi.vercel.app"

    const val LOCATIONS = "/locations"

    internal fun getUrl(endpoint: String): String = "$BASE_URL${endpoint}"
}