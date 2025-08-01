package com.shah.onboardinganimations.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shah.onboardinganimations.data.model.OnboardingCard

/**
 * Created by Monil on 01/08/25.
 */


@Composable
fun OnboardingCard(
    isExpanded: Boolean,
    onboardingCard: OnboardingCard,
    backgroundColor: Color,
    strokeColors: List<Color>,
    onCardClick: () -> Unit,
) {
    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) 444.dp else 68.dp,
        animationSpec = tween(durationMillis = 500)
    )

    val strokeBrush = Brush.verticalGradient(
        colors = strokeColors
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(
                brush = strokeBrush,
                shape = RoundedCornerShape(28.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onCardClick()
            }
            .padding(1.dp),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .background(backgroundColor, RoundedCornerShape(28.dp))
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {
                val density = LocalDensity.current
                val containerWidth = with(density) { constraints.maxWidth.toDp() }

                val targetImageWidth = if (isExpanded) containerWidth - 32.dp else 32.dp
                val targetImageHeight = if (isExpanded) 340.dp else 36.dp
                val targetOffsetX =
                    if (isExpanded) (containerWidth - targetImageWidth) / 2 else 16.dp
                val targetOffsetY =
                    if (isExpanded) 16.dp else (cardHeight - targetImageHeight) / 2

                val imageWidth by animateDpAsState(
                    targetValue = targetImageWidth,
                    animationSpec = tween(500)
                )
                val imageHeight by animateDpAsState(
                    targetValue = targetImageHeight,
                    animationSpec = tween(500)
                )
                val imageOffsetX by animateDpAsState(
                    targetValue = targetOffsetX,
                    animationSpec = tween(500)
                )
                val imageOffsetY by animateDpAsState(
                    targetValue = targetOffsetY,
                    animationSpec = tween(500)
                )

                Box(
                    modifier = Modifier
                        .offset(x = imageOffsetX, y = imageOffsetY)
                        .width(imageWidth)
                        .height(imageHeight)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(onboardingCard.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                androidx.compose.animation.AnimatedVisibility(
                    visible = !isExpanded,
                    enter = fadeIn(tween(300)),
                    exit = fadeOut(tween(300)),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Spacer(modifier = Modifier.width(48.dp)) // leave space for image

                        Text(
                            text = onboardingCard.collapsedStateText,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        )

                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                androidx.compose.animation.AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn(tween(300, delayMillis = 100)),
                    exit = fadeOut(tween(150))
                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = imageOffsetX + imageHeight + 16.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = onboardingCard.expandStateText,
                            fontSize = 20.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

