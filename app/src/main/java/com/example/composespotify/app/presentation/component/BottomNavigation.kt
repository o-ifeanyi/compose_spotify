package com.example.composespotify.app.presentation.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.example.composespotify.core.navigation.AppScreens
import com.example.composespotify.core.navigation.bottomNavItems

@Composable
fun BottomNavigation(controller: NavHostController, currentDestination: NavDestination?) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary) {

        bottomNavItems.forEach { screen ->
            val selected = currentDestination?.route == screen.route
            NavigationBarItem(
                modifier = Modifier.testTag(screen.testTag),
                selected = selected,
                icon = if (selected) screen.icon else screen.inactiveIcon,
                label = { Text(text = screen.label) },
                onClick = {
                    controller.navigate(screen.route) {
                        popUpTo(AppScreens.HomeScreen.name) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = MaterialTheme.colorScheme.tertiary
                )
            )
        }
    }
}