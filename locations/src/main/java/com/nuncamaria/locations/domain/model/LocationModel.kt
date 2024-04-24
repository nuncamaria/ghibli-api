package com.nuncamaria.locations.domain.model

data class LocationModel(
    val id: String = "",
    val name: String = "",
    val climate: String = "",
    val terrain: String = "",
    val surfaceWater: String = "",
    val residents: List<String> = listOf()
)