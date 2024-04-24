package com.nuncamaria.people.data

object ApiConstants {
    internal const val BASE_URL = "https://ghibliapi.vercel.app"

    const val PEOPLE = "/people"

    internal fun getUrl(endpoint: String): String = "$BASE_URL${endpoint}"
}