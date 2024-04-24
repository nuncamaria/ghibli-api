package com.nuncamaria.people.data.response

import com.nuncamaria.people.domain.model.PersonModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
    val id: String = "",
    val name: String = "",
    val gender: String = "",
    val age: String = "",
    @SerialName("eye_color")
    val eyeColor: String = "",
    @SerialName("hair_color")
    val hairColor: String = "",
    val films: List<String> = listOf(),
    val species: String = "",
    val url: String = "",
) {
    fun toModel(): PersonModel =
        PersonModel(id, name, gender, age, eyeColor, hairColor, films, species)
}
