package com.example.composespotify.app.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircleOutline
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.IosShare
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.PauseCircle
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material.icons.rounded.RemoveCircleOutline
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composespotify.app.presentation.component.ImageComponent
import com.example.composespotify.app.presentation.viewmodel.PlayerViewModel
import com.example.composespotify.core.theme.ComposeSpotifyTheme
import com.example.composespotify.core.util.Config
import com.example.composespotify.core.util.toTimeString

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowPlayingScreen(
    config: Config = Config(),
    playerViewModel: PlayerViewModel = PlayerViewModel
) {
    val playState by playerViewModel.playState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent
            ),
            title = {
                Text(
                    text = playState.nowPlaying?.title ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.MoreHoriz, contentDescription = "More Button")
                }
            }
        )
    }) {
        if (playState.nowPlaying == null) {
            Box {}
        } else {
            val nowPlaying = playState.nowPlaying!!

            Column(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding() + 15.dp,
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 20.dp
                ), verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    ImageComponent(
                        url = nowPlaying.url,
                        height = config.height(p = 0.5),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = nowPlaying.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(text = nowPlaying.subtitle)
                    }
                    Icon(imageVector = Icons.Rounded.AddCircleOutline, contentDescription = "Add")
                }

                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        progress = (playState.elapsedTime / 1000).toFloat() / 29,
                        color = MaterialTheme.colorScheme.onBackground,
                        trackColor = MaterialTheme.colorScheme.surface
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = playState.elapsedTime.toTimeString(),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(text = "0:30", style = MaterialTheme.typography.labelMedium)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Shuffle,
                        contentDescription = "Shuffle",
                        modifier = Modifier.size(30.dp)
                    )
                    IconButton(
                        modifier = Modifier.size(50.dp),
                        onClick = { playerViewModel.prev() })
                    {
                        Icon(
                            imageVector = Icons.Rounded.SkipPrevious,
                            contentDescription = "Prev",
                            modifier = Modifier.size(60.dp),
                        )
                    }
                    IconButton(
                        modifier = Modifier.size(100.dp),
                        onClick = {
                            if (playState.isPlaying) {
                                playerViewModel.pause()
                            } else {
                                playerViewModel.resume()
                            }
                        }) {
                        Icon(
                            imageVector = if (playState.isPlaying) Icons.Rounded.PauseCircle else Icons.Rounded.PlayCircle,
                            contentDescription = "Play/Pause",
                            modifier = Modifier.size(100.dp),
                        )
                    }
                    IconButton(
                        modifier = Modifier.size(60.dp),
                        onClick = { playerViewModel.next() })
                    {
                        Icon(
                            imageVector = Icons.Rounded.SkipNext,
                            contentDescription = "Next",
                            modifier = Modifier.size(50.dp),
                        )
                    }
                    Icon(
                        imageVector = Icons.Rounded.RemoveCircleOutline,
                        contentDescription = "Remove",
                        modifier = Modifier.size(30.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(imageVector = Icons.Rounded.Devices, contentDescription = "Devices")
                    Icon(imageVector = Icons.Rounded.IosShare, contentDescription = "Share")
                }
            }
        }
    }
}

@Preview
@Composable
fun PlayerPreview() {
    ComposeSpotifyTheme {
        NowPlayingScreen()
    }
}