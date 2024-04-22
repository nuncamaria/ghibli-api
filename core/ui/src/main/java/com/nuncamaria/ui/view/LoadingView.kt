package com.nuncamaria.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nuncamaria.ui.theme.Spacing
import com.nuncamaria.ui.theme.Typography

@Composable
fun LoadingView() {
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
            CircularProgressIndicator(modifier = Modifier.padding(bottom = Spacing.s))

            Text(
                text = "Loading",
                style = Typography.titleLarge
            )

            Text(
                text = "Thank you for your patience",
                textAlign = TextAlign.Center,
                style = Typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun PreviewErrorView() {
    LoadingView()
}