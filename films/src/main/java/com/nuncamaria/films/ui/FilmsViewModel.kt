package com.nuncamaria.films.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuncamaria.films.domain.GetFilmsUseCase
import com.nuncamaria.films.domain.model.FilmModel
import com.nuncamaria.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmsViewModel @Inject constructor(private val getFilms: GetFilmsUseCase) : ViewModel() {

    private val _films: MutableStateFlow<UiState<List<FilmModel>>> = MutableStateFlow(UiState.Idle)
    val films: StateFlow<UiState<List<FilmModel>>> = _films.asStateFlow()

    init {
        _films.value = UiState.Loading

        viewModelScope.launch {
            getFilms()
                .onSuccess { _films.value = UiState.Success(it) }
                .onFailure { _films.value = UiState.Error(it, it.localizedMessage ?: "Error: ${::getFilms}") }
        }
    }
}