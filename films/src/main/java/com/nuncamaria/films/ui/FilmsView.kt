package com.nuncamaria.films.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nuncamaria.films.domain.model.FilmModel
import com.nuncamaria.ui.utils.UiState
import com.nuncamaria.ui.view.ErrorView

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
            is UiState.Success -> FilmsViewContent((films.value as UiState.Success<List<FilmModel>>).data)
            is UiState.Error -> ErrorView((films.value as UiState.Error).message)
            UiState.Idle -> {}
            UiState.Loading -> CircularProgressIndicator()
        }
    }
}

@Composable
fun FilmsViewContent(films: List<FilmModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(films) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it.image)
                    .crossfade(true)
                    .build(),
                contentDescription = it.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
        }
    }
}