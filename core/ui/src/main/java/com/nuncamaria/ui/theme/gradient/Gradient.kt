package com.nuncamaria.ui.theme.gradient

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import com.nuncamaria.ui.theme.Colors

const val OFFSET_Y = 0f
const val RADIUS_GLASS = 0.6f

const val COLOR_STOP_FIRST = 0f
const val COLOR_STOP_MIDDLE = 0.5f
const val COLOR_STOP_LAST = 0.8f

val backgroundRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val maxWidth = size.width
        val maxHeight = size.height
        val maxDimension = size.maxDimension

        return RadialGradientShader(
            colors = listOf(Colors.secondary10, Colors.primary10),
            center = Offset(maxWidth, maxHeight),
            radius = maxDimension / 1F,
            colorStops = listOf(COLOR_STOP_FIRST, COLOR_STOP_LAST)
        )
    }
}

val glassRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val maxWidth = size.width
        val maxHeight = size.height
        val centerX = size.center.x
        val minDimension = minOf(maxHeight, maxWidth)

        return RadialGradientShader(
            colors = listOf(Colors.secondary10, Colors.secondary50, Colors.neutral80),
            center = Offset(centerX, OFFSET_Y),
            radius = minDimension / RADIUS_GLASS,
            colorStops = listOf(COLOR_STOP_FIRST, COLOR_STOP_MIDDLE, COLOR_STOP_LAST)
        )
    }
}