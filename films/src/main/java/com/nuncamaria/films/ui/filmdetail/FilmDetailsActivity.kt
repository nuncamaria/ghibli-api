package com.nuncamaria.films.ui.filmdetail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.nuncamaria.ui.theme.GhibliAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailsActivity : ComponentActivity() {

    private val viewModel: FilmDetailsViewModel by viewModels<FilmDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val films = viewModel.films.collectAsState()

            GhibliAPITheme {
                FilmDetailView(films.value.id) {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }
}