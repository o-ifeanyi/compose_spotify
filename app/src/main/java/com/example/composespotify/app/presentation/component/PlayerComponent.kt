package com.example.composespotify.app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composespotify.app.presentation.viewmodel.PlayerViewModel

@Composable
fun PlayerComponent(playerViewModel: PlayerViewModel = PlayerViewModel, onClick: () -> Unit) {
    val playState by playerViewModel.playState.collectAsState()

    if (playState.nowPlaying == null) {
        Box {}
    } else {
        val nowPlaying = playState.nowPlaying!!

        Button(
            modifier = Modifier.padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            contentPadding = PaddingValues(8.dp),
            onClick = { onClick.invoke() }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageComponent(
                    url = nowPlaying.url,
                    height = 50.dp,
                    width = 50.dp,
                    background = MaterialTheme.colorScheme.onSurface,
                    shape = MaterialTheme.shapes.small,
                )
                Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = nowPlaying.title,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(
                        text = nowPlaying.subtitle,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Rounded.Devices, contentDescription = "Devices")
                IconButton(onClick = {
                    if (playState.isPlaying) {
                        playerViewModel.pause()
                    } else {
                        playerViewModel.resume()
                    }
                }) {
                    Icon(imageVector = if (playState.isPlaying) Icons.Rounded.Pause else Icons.Rounded.PlayArrow, contentDescription = "Play/Pause Button" )
                }
            }
        }
    }

}