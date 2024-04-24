package com.nuncamaria.locations.domain

import com.nuncamaria.locations.data.repository.LocationsRepository
import com.nuncamaria.locations.domain.model.LocationModel
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val repository: LocationsRepository) {

    suspend operator fun invoke(): Result<List<LocationModel>> =
        repository.getLocations().map { locations ->
            locations.map { it.toModel() }
        }
}