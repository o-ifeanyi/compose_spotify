package com.example.composespotify.core.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.composespotify.core.util.TestTags
import com.example.composespotify.core.component.LogoComponent

sealed class BottomNavItem(
    val testTag: String,
    val route: String,
    val label: String,
    val icon: @Composable () -> Unit,
    val inactiveIcon: @Composable () -> Unit
) {

    object Home : BottomNavItem(
        testTag = TestTags.homeTab,
        route = AppScreens.HomeScreen.name,
        label = "Home",
        icon = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home Tab")
        },
        inactiveIcon = {
        Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home Tab")
    }
    )

    object Search : BottomNavItem(
        testTag = TestTags.searchTab,
        route = AppScreens.SearchScreen.name,
        label = "Search",
        icon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Tab")
        },
        inactiveIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search Tab")
        }
    )

    object Subscription : BottomNavItem(
        testTag = TestTags.subscriptionTab,
        route = AppScreens.SubscriptionScreen.name,
        label = "Premium",
        icon = { LogoComponent(size = 24.dp) },
        inactiveIcon = { LogoComponent(size = 24.dp) }
    )
}

val bottomNavItems = listOf(
    BottomNavItem.Home,
    BottomNavItem.Search,
    BottomNavItem.Subscription
)