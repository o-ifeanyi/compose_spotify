package com.example.composespotify.core.util

import androidx.compose.ui.graphics.Color
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        blue = Random.nextFloat(),
        green = Random.nextFloat()
    )
}

fun Int.toTimeString() : String {
    val seconds = this / 1000 % 60
    return "0:${seconds.toString().padStart(2, '0')}"
}