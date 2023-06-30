package com.example.composespotify.features.presentation.component

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
import com.example.composespotify.features.data.model.SubOfferModel
import com.example.composespotify.features.data.model.gradient

@Composable
fun SubOfferComponent(model: SubOfferModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(140.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "FREE", style = MaterialTheme.typography.labelMedium, color = Color.White)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = model.free,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, fill = true)
                .background(
                    brush = model.gradient(),
                    shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "PREMIUM", style = MaterialTheme.typography.labelMedium, color = Color.White)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = model.premium,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
