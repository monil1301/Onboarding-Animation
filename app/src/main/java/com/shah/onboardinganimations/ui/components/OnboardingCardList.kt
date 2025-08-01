package com.shah.onboardinganimations.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.shah.onboardinganimations.data.model.OnboardingCard
import com.shah.onboardinganimations.data.model.SaveButtonCta
import kotlinx.coroutines.delay

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun OnboardingCardList(
    cards: List<OnboardingCard>,
    expandCardStayInterval: Int,
    bottomToCenterTranslationInterval: Int,
    collapseExpandIntroInterval: Int,
    saveButtonCta: SaveButtonCta,
    toolBarIcon: String,
    toolBarText: String,
    introTitle: String,
    introSubtitle: String,
    onNext: () -> Unit
) {
    var expandedIndex by remember { mutableIntStateOf(-1) }
    var visibleCardCount by remember { mutableIntStateOf(0) }
    var isAnimating by remember { mutableStateOf(true) }

    // Configurable animation timings
    val delayBeforeStart = 2500L

    // Launch the entrance animation
    LaunchedEffect(Unit) {
        delay(delayBeforeStart)

        cards.forEachIndexed { index, _ ->
            visibleCardCount = index + 1
            expandedIndex = index

            delay(expandCardStayInterval.toLong())

            if (index != cards.lastIndex) {
                expandedIndex = -1 // collapse current
                delay(collapseExpandIntroInterval.toLong())
            }
        }

        // End of sequence
        isAnimating = false
    }

    val activeTheme = cards.getOrNull(expandedIndex.takeIf { it >= 0 } ?: 0)

    var introTextVisible by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color((activeTheme?.startGradient ?: "#322B47").toColorInt()),
                        Color((activeTheme?.endGradient ?: "#322B47").toColorInt()),
                    )
                )
            )
    ) {
        LaunchedEffect(Unit) {
            while (introTextVisible) {
                delay(2000)
                introTextVisible = !introTextVisible
            }
        }

        AnimatedVisibility(
            visible = introTextVisible,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(500)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            WelcomeText(introTitle, introSubtitle)
        }

        Scaffold(
            topBar = {
                if (!introTextVisible)
                    TopBar(toolBarIcon, toolBarText) {}
            },
            containerColor = Color.Transparent
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(cards.size) { index ->
                    AnimatedVisibility(
                        visible = index < visibleCardCount,
                        enter = slideInVertically(
                            initialOffsetY = { fullHeight -> fullHeight }, // slide from bottom
                            animationSpec = tween(durationMillis = bottomToCenterTranslationInterval)
                        ) + fadeIn(),
                        exit = ExitTransition.None
                    ) {
                        OnboardingCard(
                            isExpanded = index == expandedIndex,
                            onCardClick = {
                                if (!isAnimating) {
                                    expandedIndex = if (expandedIndex == index) -1 else index
                                }
                            },
                            backgroundColor = Color(
                                (activeTheme?.backGroundColor ?: "#000000").toColorInt()
                            ).copy(0.3f),
                            strokeColors = listOf(
                                Color((activeTheme?.strokeStartColor ?: "#000000").toColorInt()),
                                Color((activeTheme?.strokeEndColor ?: "#ffffff").toColorInt())
                            ),
                            onboardingCard = cards[index]
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        if (!isAnimating)
            SaveGoldButton(Modifier.align(Alignment.BottomCenter), saveButtonCta, onNext)
    }
}