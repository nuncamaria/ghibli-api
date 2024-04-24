package com.nuncamaria.people.domain

import com.nuncamaria.people.data.repository.PeopleRepository
import com.nuncamaria.people.domain.model.PersonModel
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(private val repository: PeopleRepository) {

    suspend operator fun invoke(): Result<List<PersonModel>> =
        repository.getPeople().map { people ->
            people.map { it.toModel() }
        }
}