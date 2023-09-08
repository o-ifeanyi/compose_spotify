package com.example.composespotify.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composespotify.app.presentation.component.BottomNavigation
import com.example.composespotify.app.presentation.component.PlayerComponent
import com.example.composespotify.core.navigation.AppNavigation
import com.example.composespotify.core.navigation.AppScreens
import com.example.composespotify.core.navigation.bottomNavItems
import com.example.composespotify.core.theme.ComposeSpotifyTheme
import com.example.composespotify.core.service.SnackBarService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSpotifyTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    val controller = rememberNavController()
                    val navBackStackEntry = controller.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry.value?.destination

                    val hidePlayerRoutes = listOf(AppScreens.AuthScreen, AppScreens.NowPlayingScreen)



                    AppNavigation(controller = controller)
                    Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                        AnimatedVisibility(
                            visible = hidePlayerRoutes.all {
                                it.name != currentDestination?.route
                            },
                            enter = slideInVertically { it },
                            exit = slideOutVertically { it },
                        ) {
                            PlayerComponent {
                                controller.navigate(AppScreens.NowPlayingScreen.name)
                            }
                        }
                        AnimatedVisibility(
                            visible = bottomNavItems.any {
                                it.route == currentDestination?.route
                            },
                            enter = slideInVertically { it },
                            exit = slideOutVertically { it },
                        ) {
                            BottomNavigation(controller, currentDestination)
                        }
                    }

                    val snackBarState = SnackBarService.snackBarState.collectAsState().value

                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.TopCenter),
                        visible = snackBarState.hasMessage,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        Snackbar { Text(text = snackBarState.message) }
                    }
                }
            }
        }
    }
}