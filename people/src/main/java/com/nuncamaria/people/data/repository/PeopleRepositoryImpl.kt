package com.nuncamaria.people.data.repository

import com.nuncamaria.people.data.datasource.PeopleCloudDataSource
import com.nuncamaria.people.data.response.PersonResponse
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val dataSource: PeopleCloudDataSource) : PeopleRepository {

    override suspend fun getPeople(): Result<List<PersonResponse>> =
        dataSource.getPeople()
}