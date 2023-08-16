package com.example.composespotify.app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composespotify.app.data.model.SubscriptionModel
import com.example.composespotify.app.data.model.gradient

@Composable
fun SubCardComponent(model: SubscriptionModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = model.gradient(), shape = MaterialTheme.shapes.medium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            model.tag?.let {
                Box(
                    modifier = Modifier.background(
                        color = Color.White.copy(0.7f),
                        shape = RoundedCornerShape(4.dp)
                    )
                ) {
                    Text(
                        text = it,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(8.dp),
                        color = Color.Black
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = model.subtitle,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}