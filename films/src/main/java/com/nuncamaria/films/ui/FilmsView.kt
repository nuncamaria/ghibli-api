package com.nuncamaria.films.ui

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nuncamaria.films.domain.model.FilmModel
import com.nuncamaria.films.ui.filmdetail.FilmDetailsActivity
import com.nuncamaria.ui.theme.Colors
import com.nuncamaria.ui.theme.Spacing
import com.nuncamaria.ui.theme.Typography
import com.nuncamaria.ui.utils.UiState
import com.nuncamaria.ui.view.ErrorView
import com.nuncamaria.ui.view.LoadingView

@Composable
fun FilmsView() {
    val viewModel = hiltViewModel<FilmsViewModel>()
    val films = viewModel.films.collectAsState()

    val ctx = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.md),
        color = Colors.transparent
    ) {
        when (films.value) {
            UiState.Idle -> {}
            UiState.Loading -> LoadingView()
            is UiState.Success -> {
                FilmsViewContent(
                    films = (films.value as UiState.Success<List<FilmModel>>).data
                ) {
                    ctx.startActivity(Intent(ctx, FilmDetailsActivity::class.java))
                }
            }

            is UiState.Error -> {
                ErrorView((films.value as UiState.Error).message) {
                    viewModel.getFilms()
                }
            }
        }
    }
}

@Composable
fun FilmsViewContent(films: List<FilmModel>, onFilmClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(Spacing.md),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.md)) {
                Text(
                    modifier = Modifier.padding(top = Spacing.md),
                    text = "Find your favorite Ghibli films",
                    style = Typography.displayMedium
                )

                Text(
                    modifier = Modifier
                        .widthIn(max = screenWidth / 1.5F)
                        .align(Alignment.End)
                        .padding(bottom = Spacing.lg),
                    text = "This is an app connected to the Studio Ghibli API. " +
                            "It's purpose is to display information with a clear and aesthetic UI",
                    style = Typography.bodyMedium
                )
            }
        }

        items(films) {
            Card(
                modifier = Modifier.clickable { onFilmClick() },
                colors = CardDefaults.outlinedCardColors()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = it.title,
                    modifier = Modifier.fillMaxWidth(),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(Spacing.md),
                    verticalArrangement = Arrangement.spacedBy(Spacing.xxs)
                ) {
                    Text(
                        text = it.title,
                        style = Typography.headlineSmall
                    )

                    Row {
                        Text(
                            text = "${it.releaseDate} · ${it.rtScore} ★",
                            color = Colors.neutral60,
                            style = Typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}