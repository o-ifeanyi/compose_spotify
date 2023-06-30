package com.example.composespotify.features.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.composespotify.features.domain.entity.FeedType
import com.example.composespotify.features.domain.entity.HomeFeedData

@Composable
fun CategoryComponent(title: String, data: List<HomeFeedData>, onClick: (HomeFeedData) -> Unit) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        items(data) { item ->
            Column(
                modifier = Modifier.width(180.dp)
            ) {
                Button(
                    onClick = { onClick.invoke(item) },
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    ImageComponent(url = item.url)
                }

                Spacer(modifier = Modifier.height(5.dp))

                when (item.type) {
                    FeedType.Playlist -> {
                        Text(
                            text = item.header,
                            maxLines = 2,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                        )
                    }
                     FeedType.Album -> {
                         Text(
                             text = item.header,
                             maxLines = 1,
                             overflow = TextOverflow.Ellipsis,
                             style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                             color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                         )
                         Text(
                             text = "Album • ${item.subtitle}",
                             maxLines = 1,
                             style = MaterialTheme.typography.bodyMedium,
                             overflow = TextOverflow.Ellipsis,
                             color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                         )
                     }
                }
            }
        }
    }
}