package com.shah.onboardinganimations.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * Created by Monil on 01/08/25.
 */


@Composable
fun OnboardingCard(
    isExpanded: Boolean,
    onCardClick: () -> Unit,
    title: String,
    imageUrl: String
) {
    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) 444.dp else 68.dp,
        animationSpec = tween(durationMillis = 500)
    )

    val imageHeight by animateDpAsState(
        targetValue = if (isExpanded) 340.dp else 36.dp,
        animationSpec = tween(durationMillis = 500)
    )

    val imageWidth by animateDpAsState(
        targetValue = if (isExpanded) 340.dp else 32.dp,
        animationSpec = tween(durationMillis = 500)
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(cardHeight)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onCardClick()
            },
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val density = LocalDensity.current
            val containerWidth = with(density) { constraints.maxWidth.toDp() }

            val imageOffsetX by animateDpAsState(
                targetValue = if (isExpanded) (containerWidth - imageWidth) / 2 else 16.dp,
                animationSpec = tween(durationMillis = 500)
            )

            Box(
                modifier = Modifier
                    .offset(x = imageOffsetX)
                    .width(imageWidth)
                    .height(imageHeight)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            if (!isExpanded) {
                Text(
                    text = title,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 48.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewOnboardingCard() {
    OnboardingCard(false, {}, "collapsed", "")
}
