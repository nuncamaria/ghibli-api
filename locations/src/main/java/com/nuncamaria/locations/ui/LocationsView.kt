package com.nuncamaria.locations.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nuncamaria.locations.domain.model.LocationModel
import com.nuncamaria.ui.theme.Spacing
import com.nuncamaria.ui.theme.Typography
import com.nuncamaria.ui.utils.UiState
import com.nuncamaria.ui.view.ErrorView
import com.nuncamaria.ui.view.LoadingView

@Composable
fun LocationsView() {
    val viewModel = hiltViewModel<LocationsViewModel>()
    val locations = viewModel.locations.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.md),
        color = MaterialTheme.colorScheme.background
    ) {
        when (locations.value) {
            UiState.Idle -> {}
            UiState.Loading -> LoadingView()
            is UiState.Success -> FilmsViewContent((locations.value as UiState.Success<List<LocationModel>>).data)
            is UiState.Error -> {
                ErrorView((locations.value as UiState.Error).message) {
                    viewModel.getLocations()
                }
            }
        }
    }
}

@Composable
fun FilmsViewContent(locations: List<LocationModel>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(Spacing.md)) {
        item {
            Text(
                modifier = Modifier.padding(top = Spacing.md),
                text = "Locations",
                style = Typography.displayMedium
            )
        }

        items(locations) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(Spacing.md)) {
                    Text(text = it.name)
                    Text(text = it.climate)
                    Text(text = it.terrain)
                }
            }
        }
    }
}