package com.nuncamaria.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nuncamaria.ui.theme.Spacing
import com.nuncamaria.ui.theme.Typography

@Composable
fun ErrorView(message: String, onErrorClick: (() -> Unit)? = null) {
    Card(
        modifier = Modifier.wrapContentSize(),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier.padding(Spacing.lg),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.s, alignment = Alignment.CenterVertically)
        ) {
            Image(
                modifier = Modifier
                    .padding(bottom = Spacing.s)
                    .size(Spacing.xxjumbo),
                imageVector = Icons.Default.Error,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red)
            )

            Text(
                text = "Error",
                style = Typography.titleLarge
            )

            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = Typography.bodyLarge
            )

            onErrorClick?.let {
                Button(onClick = onErrorClick) {
                    Text(text = "Try again")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewErrorView() {
    ErrorView("Error message")
}