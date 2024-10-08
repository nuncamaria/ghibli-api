package com.nuncamaria.people.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nuncamaria.people.domain.model.PersonModel
import com.nuncamaria.ui.theme.Colors
import com.nuncamaria.ui.theme.Spacing
import com.nuncamaria.ui.theme.Typography
import com.nuncamaria.ui.utils.UiState
import com.nuncamaria.ui.view.ErrorView
import com.nuncamaria.ui.view.LoadingView

@Composable
fun PeopleView() {
    val viewModel = hiltViewModel<PeopleViewModel>()
    val people = viewModel.people.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Spacing.md),
        color = Colors.transparent
    ) {
        when (people.value) {
            UiState.Idle -> {}
            UiState.Loading -> LoadingView()
            is UiState.Success -> PeopleViewContent((people.value as UiState.Success<List<PersonModel>>).data)
            is UiState.Error -> {
                ErrorView((people.value as UiState.Error).message) {
                    viewModel.getPeople()
                }
            }
        }
    }
}

@Composable
fun PeopleViewContent(locations: List<PersonModel>) {
    Column {
        Text(
            modifier = Modifier.padding(vertical = Spacing.md),
            text = "Meet the people",
            style = Typography.displayMedium
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(Spacing.md)) {

            items(locations) {

                Card(colors = CardDefaults.outlinedCardColors()) {

                    Column(modifier = Modifier.padding(Spacing.md)) {

                        Text(
                            text = it.name,
                            style = Typography.headlineSmall
                        )
                        Text(text = it.age)
                        Text(text = it.gender)
                    }
                }
            }
        }
    }
}