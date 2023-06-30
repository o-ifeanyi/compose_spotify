package com.example.composespotify.features.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composespotify.core.theme.ComposeSpotifyTheme
import com.example.composespotify.features.presentation.view.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    input: MutableState<String>,
    label: String,
    background: Color = MaterialTheme.colorScheme.surface,
    obscureText: Boolean = false,
    leadingIcon: ImageVector? = null,
    suffix: @Composable() (() -> Unit)? = null
) {
    TextField(
        value = input.value,
        placeholder = {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        },
        onValueChange = {
            input.value = it
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.labelMedium,
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .fillMaxWidth(),
        leadingIcon = {
            if (leadingIcon != null) Icon(
                imageVector = leadingIcon,
                contentDescription = " Prefix Icon",
                tint = MaterialTheme.colorScheme.outline,
            )
        },
        suffix = suffix,
        visualTransformation = if (obscureText) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = background,
            unfocusedContainerColor = background
        )
    )
}

@Composable
@Preview
fun Prev() {
    val search = remember {
        mutableStateOf("")
    }
    ComposeSpotifyTheme(darkTheme = true) {
        Column {
            InputField(
                input = search,
                label = "What do you want to listen to?",
                leadingIcon = Icons.Outlined.Search,
            )
        }
    }
}