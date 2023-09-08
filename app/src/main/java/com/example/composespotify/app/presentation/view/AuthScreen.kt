package com.example.composespotify.app.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composespotify.core.navigation.AppScreens
import com.example.composespotify.core.component.LogoComponent
import com.example.composespotify.app.presentation.viewmodel.AuthViewModel

@Composable
fun AuthScreen(controller: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    val state = viewModel.authState.collectAsState().value

    LaunchedEffect(key1 = state.tokenIsValid) {
        if (state.tokenIsValid) controller.navigate(AppScreens.HomeScreen.name) {
            popUpTo(AppScreens.AuthScreen.name) { inclusive = true }
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = it.calculateTopPadding(), horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoComponent()
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Millions of songs.\nFree on Spotify.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                enabled = !state.gettingToken,
                onClick = viewModel::getAndSetToken, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = .7f)
                ), modifier = Modifier.fillMaxWidth()
            ) {
                if (state.gettingToken) CircularProgressIndicator()
                else Text(
                    text = "Sign up free",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
