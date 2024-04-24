package com.nuncamaria.locations.data.response

import com.nuncamaria.locations.domain.model.LocationModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val id: String = "",
    val name: String = "",
    val climate: String = "",
    val terrain: String = "",
    @SerialName("surface_water")
    val surfaceWater: String = "",
    val residents: List<String> = listOf(),
    val films: List<String> = listOf(),
    val url: String = "",
) {
    fun toModel(): LocationModel =
        LocationModel(id, name, climate, terrain, surfaceWater, residents)
}
