package com.example.composespotify.app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composespotify.app.domain.entity.FeedType
import com.example.composespotify.app.presentation.viewmodel.SearchViewModel
import com.example.composespotify.core.navigation.AppScreens

enum class SearchTabs {
    Artist,
    Songs,
    Playlist,
    Albums,
}

@Composable
fun SearchResultComponent(
    controller: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state = searchViewModel.searchState.collectAsState().value
    val selectedTab = remember {
        mutableStateOf(SearchTabs.Artist)
    }

    if (state.searching) {
        CircularProgressIndicator()
    } else if (state.searchingErr.isNotEmpty()) {
        Text(text = "An error occurred")
    } else {
        val searchEntity = state.searchEntity!!
        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(SearchTabs.values()) {
                    val selected = selectedTab.value == it
                    PillComponent(text = it.name, selected = selected, onClick = {
                        selectedTab.value = it
                    })
                }
            }
            when (selectedTab.value) {
                SearchTabs.Artist -> LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(searchEntity.artists) { item ->
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ImageComponent(
                                url = item.url,
                                width = 60.dp,
                                height = 60.dp,
                                shape = RoundedCornerShape(50)
                            )
                            Text(
                                text = item.title,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Rounded.ArrowForwardIos,
                                contentDescription = "Open Artist"
                            )
                        }
                    }
                }

                SearchTabs.Songs -> LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(searchEntity.tracks) { item ->
                        TrackComponent(
                            title = item.title,
                            subtitle = item.subtitle,
                            imageUrl = item.url
                        )
                    }
                }

                SearchTabs.Playlist -> LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(searchEntity.playlists) { item ->
                        PlaylistAlbumComponent(
                            title = item.title,
                            imageUrl = item.url
                        ) {
                            controller.navigate(AppScreens.DetailScreen.name + "/${item.id}/${FeedType.Playlist.name}")
                        }
                    }
                }

                SearchTabs.Albums -> LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(searchEntity.albums) { item ->
                        PlaylistAlbumComponent(
                            title = item.title,
                            imageUrl = item.url
                        ) {
                            controller.navigate(AppScreens.DetailScreen.name + "/${item.id}/${FeedType.Album.name}")
                        }
                    }
                }
            }
        }
    }
}

