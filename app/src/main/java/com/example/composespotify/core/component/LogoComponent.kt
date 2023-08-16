package com.example.composespotify.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composespotify.R

@Composable
fun LogoComponent(size: Dp = 60.dp) {
    Image(
        painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.logo_white else R.drawable.logo_black),
        contentDescription = "Logo",
        modifier = Modifier.size(size)
    )
}