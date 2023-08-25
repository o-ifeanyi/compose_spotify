package com.example.composespotify.app.presentation.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PillComponent(text: String, selected: Boolean = false, onClick: () -> Unit = {}) {
    Button(
        modifier = Modifier,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.surface
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) Color.White else MaterialTheme.typography.bodyMedium.color,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        )
    }
}