package com.nuncamaria.films.ui.filmdetail

import androidx.lifecycle.ViewModel
import com.nuncamaria.films.domain.model.FilmModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor() : ViewModel() {

    private val _films: MutableStateFlow<FilmModel> = MutableStateFlow(FilmModel())
    val films: StateFlow<FilmModel> = _films.asStateFlow()

    init {
        getFilms()
    }

    fun getFilms() {

        _films.value = FilmModel(
            id = "2baf70d1-42bb-4437-b551-e5fed5a87abe",
            title = "Castle in the Sky"
        )
    }
}