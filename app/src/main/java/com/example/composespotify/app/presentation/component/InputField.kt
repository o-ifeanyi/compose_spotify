package com.example.composespotify.app.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    input: MutableStateFlow<String>,
    label: String,
    background: Color = MaterialTheme.colorScheme.surface,
    obscureText: Boolean = false,
    leadingIcon: ImageVector? = null,
    suffix: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = input.collectAsState().value,
        placeholder = {
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        },
        onValueChange = {
            Log.d("SEARCH TYPING", it)
            input.value = it
            onValueChange.invoke(it)
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
            unfocusedContainerColor = background,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            disabledTextColor = Color.Gray,
        )
    )
}
