package com.example.composespotify.features.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composespotify.core.navigation.AppScreens
import com.example.composespotify.core.theme.ComposeSpotifyTheme
import com.example.composespotify.core.util.randomColor
import com.example.composespotify.features.presentation.component.ImageComponent
import com.example.composespotify.features.presentation.component.InputField
import com.example.composespotify.features.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    controller: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val categories = searchViewModel.categories.collectAsLazyPagingItems()
    val search = remember {
        mutableStateOf("")
    }
    Scaffold {
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding() + 15.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 80.dp
            ), verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.titleLarge
                )
                Icon(imageVector = Icons.Outlined.CameraAlt, contentDescription = "Camera")
            }

            InputField(
                input = search,
                label = "What do you want to listen to?",
                leadingIcon = Icons.Outlined.Search,
                background = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Browse all",
                fontWeight = FontWeight.Bold
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {

                items(categories.itemSnapshotList) { model ->
                    model?.let {
                        Box(
                            modifier = Modifier
                                .height(100.dp)
                                .background(color = randomColor(), shape = MaterialTheme.shapes.small)
                                .clipToBounds()
                                .clickable {
                                    controller.navigate(AppScreens.CategoryPlaylistScreen.name + "/${it.id}/${it.name}")
                                }
                        ) {
                            Text(
                                text = it.name,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(10.dp)
                            )

                            ImageComponent(
                                modifier = Modifier
                                    .rotate(30f)
                                    .align(BiasAlignment(1.25f, 1f)),
                                url = it.icons.first().url,
                                height = 75.dp,
                                width = 75.dp,
                                shape = MaterialTheme.shapes.small
                            )

                        }
                    }
                }
                fun manageState(state: LoadState) {
                    when (state) {
                        is LoadState.Loading -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                LinearProgressIndicator(modifier = Modifier.padding(vertical = 6.dp))
                            }
                        }
                        is LoadState.Error -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Text(
                                    text = "An error occurred",
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                        else -> {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box {}
                            }
                        }
                    }
                }
                manageState(categories.loadState.refresh)
                manageState(categories.loadState.append)
            }
        }
    }
}

@Composable
@Preview
fun Prev() {
    ComposeSpotifyTheme() {
        SearchScreen(controller = NavHostController(LocalContext.current))
    }
}