package com.example.composespotify.features.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImageComponent(
    url: String,
    height: Dp = 180.dp,
    width: Dp = 180.dp,
    background: Color = MaterialTheme.colorScheme.surface,
    shape: Shape? = null
) {
    Image(
        modifier = Modifier
            .height(height)
            .width(width)
            .background(
                color = background,
                shape = shape ?: RoundedCornerShape(0.dp)
            ).clip(shape = shape ?: RoundedCornerShape(0.dp)),
        painter = rememberAsyncImagePainter(model = url),
        contentDescription = "Network Image",
        contentScale = ContentScale.Crop
    )
}