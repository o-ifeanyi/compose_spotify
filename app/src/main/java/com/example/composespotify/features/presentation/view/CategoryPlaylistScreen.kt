package com.example.composespotify.features.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composespotify.core.navigation.AppScreens
import com.example.composespotify.features.data.model.toCategoryPlaylist
import com.example.composespotify.features.presentation.component.ImageComponent
import com.example.composespotify.features.presentation.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryPlaylistScreen(
    controller: NavHostController,
    id: String,
    title: String,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val categoryPlaylist = searchViewModel.getCategoryPlaylist(id).collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = { Text(text = title, style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(
                top = it.calculateTopPadding() + 15.dp,
                start = 15.dp,
                end = 15.dp,
            ),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {

            items(categoryPlaylist.itemSnapshotList) { model ->
                model?.let { playlist ->
                    val item = playlist.toCategoryPlaylist()
                    item?.let {
                        Column {
                            Button(
                                onClick = {
                                    controller.navigate(AppScreens.DetailScreen.name + "/${item.id}/${item.type.name}")
                                },
                                contentPadding = PaddingValues(0.dp),
                                shape = RoundedCornerShape(0.dp)
                            ) {
                                ImageComponent(url = item.url)
                            }

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = item.header,
                                maxLines = 2,
                                style = MaterialTheme.typography.bodyMedium,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                            )
                        }
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
            manageState(categoryPlaylist.loadState.refresh)
            manageState(categoryPlaylist.loadState.append)
        }
    }
}