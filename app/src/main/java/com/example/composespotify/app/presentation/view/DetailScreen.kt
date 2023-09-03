package com.example.composespotify.app.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composespotify.R
import com.example.composespotify.core.component.SnackBarComponent
import com.example.composespotify.core.util.Config
import com.example.composespotify.app.presentation.component.ImageComponent
import com.example.composespotify.app.presentation.component.TrackComponent
import com.example.composespotify.app.presentation.viewmodel.DetailViewModel
import com.example.composespotify.app.presentation.viewmodel.PlayerViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: String,
    type: String,
    config: Config = Config(),
    detailViewModel: DetailViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = PlayerViewModel,
) {
    val state = detailViewModel.detailState.collectAsState().value
    val isPlaylist = type == "Playlist"
    val isAlbum = type == "Album"

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = state.gettingAlbumErr, key2 = state.gettingPlaylistErr) {
        if (state.gettingAlbumErr.isNotEmpty()) {
            scope.launch {
                snackBarHostState.showSnackbar(message = state.gettingAlbumErr)
            }
        }
        if (state.gettingPlaylistErr.isNotEmpty()) {
            scope.launch {
                snackBarHostState.showSnackbar(message = state.gettingPlaylistErr)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        if (isAlbum) {
            detailViewModel.fetchAlbum(id)
        } else if (isPlaylist) {
            detailViewModel.fetchPlaylist(id)
        }
    }

    Scaffold(
        snackbarHost = { SnackBarComponent(hostState = snackBarHostState, isError = true) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ), title = {},
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) {
        if ((isPlaylist && state.gettingPlaylist) || (isAlbum && state.gettingAlbum)) {
            CircularProgressIndicator()
        } else if (state.gettingAlbumErr.isNotEmpty() || state.gettingPlaylistErr.isNotEmpty()) {
            Text(text = "An error occurred")
        } else {
            val detailEntity = state.detailEntity!!

            LazyColumn(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxWidth(),
                contentPadding = PaddingValues(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ImageComponent(
                        url = detailEntity.url,
                        height = config.height(0.33),
                        width = config.width(0.65)
                    )
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = detailEntity.description,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isAlbum) {
                                ImageComponent(
                                    url = detailEntity.url,
                                    height = 34.dp,
                                    width = 34.dp,
                                    shape = CircleShape
                                )
                                Text(text = detailEntity.name, fontWeight = FontWeight.SemiBold)
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.logo_green),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(34.dp)
                                )
                                Text(text = "Spotify", fontWeight = FontWeight.SemiBold)
                            }
                        }
                        Text(
                            text = if (isAlbum) "Album • 2020" else "7,290,659 saves • 3h 59m",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.AddCircleOutline,
                                contentDescription = "Add"
                            )
                            Icon(
                                imageVector = Icons.Outlined.ArrowCircleDown,
                                contentDescription = "Download"
                            )
                            Icon(
                                imageVector = Icons.Outlined.MoreHoriz,
                                contentDescription = "More"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Outlined.Shuffle,
                                    contentDescription = "Download"
                                )
                            }
                            IconButton(
                                modifier = Modifier
                                    .size(34.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.tertiary,
                                        shape = CircleShape
                                    ), onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Rounded.PlayArrow,
                                    contentDescription = "Play"
                                )
                            }
                        }
                    }
                }

                items(detailEntity.tracks) { item ->
                    TrackComponent(
                        title = item.title,
                        subtitle = item.subtitle,
                        imageUrl = item.url
                    ) {
                        playerViewModel.setQueue(detailEntity.tracks)
                        playerViewModel.play(item)
                    }
                }
            }
        }
    }
}

