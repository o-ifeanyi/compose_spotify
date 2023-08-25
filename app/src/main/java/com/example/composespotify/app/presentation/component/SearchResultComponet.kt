package com.example.composespotify.app.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

enum class SearchTabs {
    Artist,
    Songs,
    Playlist,
    Albums,
}

@Composable
fun SearchResultComponent() {
    val selectedTab = remember {
        mutableStateOf(SearchTabs.Playlist)
    }

    Column(verticalArrangement = Arrangement.spacedBy(15.dp), modifier = Modifier.fillMaxSize()) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(start = 10.dp)
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
                contentPadding = PaddingValues(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(listOf(1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ImageComponent(
                            url = "https://i.scdn.co/image/ab67616d00001e020ebc17239b6b18ba88cfb8ca",
                            width = 60.dp,
                            height = 60.dp,
                            shape = RoundedCornerShape(50)
                        )
                        Text(text = "Ed Sheeran", fontWeight = FontWeight.SemiBold, maxLines = 1)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Rounded.ArrowForwardIos, contentDescription = "Open Artist")
                    }
                }
            }
            SearchTabs.Songs ->LazyColumn(
                contentPadding = PaddingValues(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(listOf(1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8)) {
                    TrackComponent(
                        title = "Shivers",
                        subtitle = "Ed Sheeran",
                        imageUrl = "https://i.scdn.co/image/ab67616d00001e020ebc17239b6b18ba88cfb8ca"
                    )
                }
            }
            SearchTabs.Playlist -> LazyVerticalGrid(
                contentPadding = PaddingValues(horizontal = 15.dp),
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(listOf(1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8)) {
                    Column {
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(0.dp),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            ImageComponent(url = "https://i.scdn.co/image/ab67616d00001e020ebc17239b6b18ba88cfb8ca")
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "Ed Sheeran",
                            maxLines = 2,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                        )
                    }
                }
            }
            SearchTabs.Albums -> LazyVerticalGrid(
                contentPadding = PaddingValues(horizontal = 15.dp),
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(listOf(1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8)) {
                    Column {
                        Button(
                            onClick = {},
                            contentPadding = PaddingValues(0.dp),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            ImageComponent(url = "https://i.scdn.co/image/ab67616d00001e020ebc17239b6b18ba88cfb8ca")
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = "Ed Sheeran",
                            maxLines = 2,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                        )
                    }
                }
            }
        }
    }
}
