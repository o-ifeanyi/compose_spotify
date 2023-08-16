package com.example.composespotify.app.data.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class SubscriptionModel(
    val tag: String?,
    val title: String,
    val subtitle: String,
    val color1: Color,
    val color2: Color
)

fun SubscriptionModel.gradient(): Brush {
    return Brush.linearGradient(
        colors = listOf(color1, color2),
        start = Offset.Zero,
        end = Offset.Infinite
    )
}

data class SubOfferModel(
    val free: String,
    val premium: String,
    val color1: Color = Color(0xFF055544),
    val color2: Color = Color(0xFF1ABE76)
)

fun SubOfferModel.gradient(): Brush {
    return Brush.linearGradient(
        colors = listOf(color1, color2),
        start = Offset.Zero,
        end = Offset.Infinite
    )
}

val subOffers = listOf(
    SubOfferModel(free = "Ad breaks", premium = "Ad-free music listening"),
    SubOfferModel(free = "Play in shuffle", premium = "Play in any order"),
    SubOfferModel(free = "Basic sound quality", premium = "Premium sound quality"),
    SubOfferModel(free = "Streaming only", premium = "Offline listening")
)

val subModels = listOf(
    SubscriptionModel(
        tag = "FREE FOR 1 MONTH",
        title = "Premium Individual",
        subtitle = "1 month of Premium for free • Ad-free music listening • Download to listen offline • Play songs in any order • Higher sound quality • Cancel anytime",
        color1 = Color(0xFF055544),
        color2 = Color(0xFF1ABE76),
    ),
    SubscriptionModel(
        tag = "FREE FOR 1 MONTH",
        title = "Premium Student",
        subtitle = "1 month of Premium for free • Ad-free music listening • Download to listen offline • Play songs in any order • Higher sound quality • Cancel anytime",
        color1 = Color(0xFFFA9E25),
        color2 = Color(0xFFCD8256),
    ),
    SubscriptionModel(
        tag = "FREE FOR 1 MONTH",
        title = "Premium Duo",
        subtitle = "1 month of Premium for free • 2 Premium accounts • Ad-free music listening • Download to listen offline • Play songs in any order • Higher sound quality • Cancel anytime",
        color1 = Color(0xFF5A93C4),
        color2 = Color(0xFF3B396D),
    ),
    SubscriptionModel(
        tag = "FREE FOR 1 MONTH",
        title = "Premium Family",
        subtitle = "1 month of Premium for free • Up to 6 Premium accounts • Block explicit music • Ad-free music listening • Download to listen offline • Play songs in any order • Higher sound quality • Cancel anytime",
        color1 = Color(0xFF203368),
        color2 = Color(0xFFAF2896),
    ),
    SubscriptionModel(
        tag = null,
        title = "Premium Prepaid",
        subtitle = "Choose from 1, 3, 6, or 12 months of Premium • Top up when you want",
        color1 = Color(0xFF4F9BF5),
        color2 = Color(0xFF4F9BF5),
    ),
)