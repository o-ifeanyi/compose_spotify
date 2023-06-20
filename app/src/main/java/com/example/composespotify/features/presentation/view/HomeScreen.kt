package com.example.composespotify.features.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composespotify.core.navigation.AppScreens
import com.example.composespotify.core.theme.ComposeSpotifyTheme
import com.example.composespotify.features.presentation.component.CategoryComponent
import com.example.composespotify.features.presentation.component.PillComponent
import com.example.composespotify.features.presentation.component.RecentComponent
import com.example.composespotify.core.component.SnackBarComponent
import com.example.composespotify.features.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(controller: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val state = homeViewModel.homeState.collectAsState().value
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = state.fetchingHomeFeedErr) {
        if (state.fetchingHomeFeedErr.isNotEmpty()) {
            scope.launch {
                snackBarHostState.showSnackbar(message = state.fetchingHomeFeedErr)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackBarComponent(hostState = snackBarHostState, isError = true) },
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    Text(
                        text = "Good afternoon",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                },
                title = {
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        PillComponent(text = "Music")
                        PillComponent(text = "Podcasts & Shows")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notification"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Outlined.History, contentDescription = "History")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Notification"
                        )
                    }
                }
            )
        },
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = 80.dp
                ),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(horizontal = 15.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                LazyVerticalGrid(
                    modifier = Modifier.height(200.dp),
                    columns = GridCells.Fixed(count = 2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.recommendations) { item ->
                        RecentComponent(item) { id ->
                            controller.navigate(AppScreens.DetailScreen.name + "/${id}/Album")
                        }
                    }
                }
            }
            items(state.homeFeed) { feed ->
                CategoryComponent(
                    title = feed.title,
                    data = feed.data,
                    onClick = { data->
                        controller.navigate(AppScreens.DetailScreen.name + "/${data.id}/${data.type.name}")
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun Previeww() {
    ComposeSpotifyTheme(darkTheme = true) {
        HomeScreen(controller = NavHostController(LocalContext.current))
    }
}