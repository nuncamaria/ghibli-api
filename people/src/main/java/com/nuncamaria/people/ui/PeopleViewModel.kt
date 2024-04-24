package com.nuncamaria.people.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuncamaria.people.domain.GetPeopleUseCase
import com.nuncamaria.people.domain.model.PersonModel
import com.nuncamaria.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(private val getPeopleUseCase: GetPeopleUseCase) : ViewModel() {

    private val _people: MutableStateFlow<UiState<List<PersonModel>>> = MutableStateFlow(UiState.Idle)
    val people: StateFlow<UiState<List<PersonModel>>> = _people.asStateFlow()

    init {
        getPeople()
    }

    fun getPeople() {
        _people.value = UiState.Loading

        viewModelScope.launch {
            getPeopleUseCase()
                .onSuccess { _people.value = UiState.Success(it) }
                .onFailure {
                    _people.value = UiState.Error(it, it.localizedMessage ?: "Error: ${::getPeopleUseCase}")
                }
        }
    }
}