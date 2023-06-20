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
            SubscriptionScreen(controller = controller)
        }
        val detailRoute = "${AppScreens.DetailScreen.name}/{id}/{type}"
        composable(detailRoute) {
            val id = it.arguments?.getString("id")
            val type = it.arguments?.getString("type")
            DetailScreen(id = id ?: "", type = type ?: "")
        }
    }
}