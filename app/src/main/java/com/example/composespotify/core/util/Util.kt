package com.example.composespotify.core.util

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        blue = Random.nextFloat(),
        green = Random.nextFloat()
    )
}
