package com.nuncamaria.films.ui.filmdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.nuncamaria.ui.theme.Colors
import com.nuncamaria.ui.theme.Spacing
import com.nuncamaria.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDetailView(
    filmId: String,
    onClickBack: () -> Unit
) {
    val scrollBehaviorPinned = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviorPinned.nestedScrollConnection),
        containerColor = Colors.transparent,
        topBar = {
            TopAppBar(
                title = { Text(text = "Film name") },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                },
                scrollBehavior = scrollBehaviorPinned
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it),
            contentAlignment = Alignment.Center
        ) {
            FilmDetailViewContent()
        }
    }
}

@Composable
fun FilmDetailViewContent() {

    Column(verticalArrangement = Arrangement.spacedBy(Spacing.md)) {
        Text(
            modifier = Modifier.padding(top = Spacing.md),
            text = "Detail",
            style = Typography.displayMedium
        )
    }
}