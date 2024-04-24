package com.nuncamaria.people.domain.model

data class PersonModel(
    val id: String = "",
    val name: String = "",
    val gender: String = "",
    val age: String = "",
    val eyeColor: String = "",
    val hairColor: String = "",
    val films: List<String> = listOf(),
    val species: String = "",
)