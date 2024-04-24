package com.nuncamaria.locations.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuncamaria.locations.domain.GetLocationsUseCase
import com.nuncamaria.locations.domain.model.LocationModel
import com.nuncamaria.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val getLocationsUseCase: GetLocationsUseCase) : ViewModel() {

    private val _locations: MutableStateFlow<UiState<List<LocationModel>>> = MutableStateFlow(UiState.Idle)
    val locations: StateFlow<UiState<List<LocationModel>>> = _locations.asStateFlow()

    init {
        getLocations()
    }

    fun getLocations() {
        _locations.value = UiState.Loading

        viewModelScope.launch {
            getLocationsUseCase()
                .onSuccess { _locations.value = UiState.Success(it) }
                .onFailure {
                    _locations.value = UiState.Error(it, it.localizedMessage ?: "Error: ${::getLocationsUseCase}")
                }
        }
    }
}