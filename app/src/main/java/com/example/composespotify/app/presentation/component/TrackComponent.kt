package com.example.composespotify.app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TrackComponent(
    title: String,
    subtitle: String,
    imageUrl: String?
) {
    Row(
        modifier = Modifier.height(70.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (imageUrl != null) {
            ImageComponent(url = imageUrl, width = 70.dp, height = 70.dp)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title, fontWeight = FontWeight.SemiBold, maxLines = 1)
            Text(text = subtitle, maxLines = 1)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.MoreHoriz,
            contentDescription = "More"
        )
    }
}
