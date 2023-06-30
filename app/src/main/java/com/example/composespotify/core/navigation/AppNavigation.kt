package com.example.composespotify.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composespotify.features.presentation.view.*

@Composable
fun AppNavigation(controller: NavHostController) {
    NavHost(navController = controller, startDestination = AppScreens.AuthScreen.name) {
        composable(AppScreens.AuthScreen.name) {
            AuthScreen(controller = controller)
        }
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(controller = controller)
        }
        composable(AppScreens.SearchScreen.name) {
            SearchScreen(controller = controller)
        }
        composable(AppScreens.SubscriptionScreen.name) {
            SubscriptionScreen()
        }
        val detailRoute = "${AppScreens.DetailScreen.name}/{id}/{type}"
        composable(detailRoute) {
            val id = it.arguments?.getString("id")
            val type = it.arguments?.getString("type")
            DetailScreen(id = id ?: "", type = type ?: "")
        }
        val categoryPlaylistRoute = "${AppScreens.CategoryPlaylistScreen.name}/{id}/{title}"
        composable(categoryPlaylistRoute) {
            val id = it.arguments?.getString("id")
            val title = it.arguments?.getString("title")
            CategoryPlaylistScreen(controller = controller, id = id ?: "", title = title ?: "")
        }
    }
}