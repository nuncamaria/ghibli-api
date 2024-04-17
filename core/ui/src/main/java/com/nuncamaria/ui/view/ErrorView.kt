package com.nuncamaria.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorView(message: String) {
    Column {
        Image(
            imageVector = Icons.Default.Error,
            contentDescription = null
        )

        Text(text = message)
    }
}