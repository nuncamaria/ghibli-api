package com.nuncamaria.people.data.repository

import com.nuncamaria.people.data.response.PersonResponse

interface PeopleRepository {

    suspend fun getPeople(): Result<List<PersonResponse>>
}