package com.example.composespotify.app.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composespotify.app.presentation.component.InputField
import com.example.composespotify.app.presentation.component.SearchCategoryComponent
import com.example.composespotify.app.presentation.component.SearchResultComponent
import com.example.composespotify.app.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    controller: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchText = searchViewModel.searchText.collectAsState().value

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
                input = searchViewModel.searchText,
                label = "What do you want to listen to?",
                leadingIcon = Icons.Outlined.Search,
                background = MaterialTheme.colorScheme.onBackground,
                onValueChange = { }
            )

            Box(contentAlignment = Alignment.Center) {
                if (searchText.isNotEmpty()) {
                    SearchResultComponent()
                } else {
                    SearchCategoryComponent(controller)
                }
            }
        }
    }
}


