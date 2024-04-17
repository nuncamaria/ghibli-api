package com.nuncamaria.films.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nuncamaria.films.domain.model.FilmModel
import com.nuncamaria.ui.utils.UiState

@Composable
fun FilmsView() {
    val viewModel = hiltViewModel<FilmsViewModel>()
    val films = viewModel.films.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        when (films.value) {
            is UiState.Success -> Greeting((films.value as UiState.Success<List<FilmModel>>).data[0].title)
            is UiState.Error -> Greeting((films.value as UiState.Error).message)
            UiState.Idle -> {}
            UiState.Loading -> CircularProgressIndicator()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}