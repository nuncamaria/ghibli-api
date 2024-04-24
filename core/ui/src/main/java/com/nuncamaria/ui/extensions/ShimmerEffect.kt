package com.nuncamaria.ui.extensions

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.nuncamaria.ui.theme.Colors
import com.nuncamaria.ui.theme.Shape

const val INITIAL_VALUE = -2
const val TARGET_VALUE = 2
const val DURATION_MILLIS = 1000

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }

    val transition = rememberInfiniteTransition(label = "shimmerEffect")

    val startOffsetX by transition.animateFloat(
        initialValue = INITIAL_VALUE * size.width.toFloat(),
        targetValue = TARGET_VALUE * size.width.toFloat(),
        animationSpec = infiniteRepeatable(tween(DURATION_MILLIS)),
        label = "shimmerEffect"
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Colors.neutral30,
                Colors.neutral50,
                Colors.neutral30
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        ),
        shape = Shape.small
    ).onGloballyPositioned {
        size = it.size
    }
}
