package com.example.composespotify.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SnackBarComponent(hostState: SnackbarHostState, isError: Boolean = true) {
    val theme = MaterialTheme.colorScheme
    SnackbarHost(hostState = hostState) {
        Snackbar(
            snackbarData = it,
            modifier = Modifier.padding(10.dp),
            containerColor = if (isError) theme.errorContainer else theme.tertiary,
            contentColor = Color.White
        )
    }
}
