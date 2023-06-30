package com.example.composespotify.features.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composespotify.core.theme.ComposeSpotifyTheme
import com.example.composespotify.core.util.Config
import com.example.composespotify.features.data.model.subModels
import com.example.composespotify.features.data.model.subOffers
import com.example.composespotify.features.presentation.component.SubCardComponent
import com.example.composespotify.features.presentation.component.SubOfferComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubscriptionScreen(config: Config = Config()) {
    val pagerState = rememberPagerState(pageCount = { subOffers.size })

    Scaffold {
        LazyColumn(
            modifier = Modifier.padding(
                top = it.calculateTopPadding() + 15.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 80.dp
            ), verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    text = "1 month of Premium for free",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }
            item {
                HorizontalPager(
                    state = pagerState,
                    pageSize = PageSize.Fixed(config.width(p = 0.7)),
                    pageSpacing = 15.dp
                ) { page ->
                    SubOfferComponent(model = subOffers[page])
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (i in 0 until pagerState.pageCount) {
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(8.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }

            item {
                Text(
                    "You can't upgrade to Premium in the app. We know, it's not ideal.",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Surface(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.small) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp), horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Spotify Free",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "CURRENT PLAN")
                    }
                }
            }

            items(subModels) { model ->
                SubCardComponent(model)
            }
        }
    }
}

@Composable
@Preview
fun SubscriptionScreenPrev() {
    ComposeSpotifyTheme() {
        SubscriptionScreen()
    }
}