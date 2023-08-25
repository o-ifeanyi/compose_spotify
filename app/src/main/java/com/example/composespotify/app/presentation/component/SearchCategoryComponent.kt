package com.example.composespotify.app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composespotify.app.presentation.viewmodel.SearchViewModel
import com.example.composespotify.core.navigation.AppScreens
import com.example.composespotify.core.util.randomColor

@Composable
fun SearchCategoryComponent(
    controller: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val categories = searchViewModel.categories.collectAsLazyPagingItems()

    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
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
                            .background(
                                color = randomColor(),
                                shape = MaterialTheme.shapes.small
                            )
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
