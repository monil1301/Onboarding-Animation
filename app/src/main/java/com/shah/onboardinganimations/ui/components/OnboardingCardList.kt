package com.shah.onboardinganimations.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shah.onboardinganimations.data.model.OnboardingCard
import androidx.core.graphics.toColorInt

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun OnboardingCardList(cards: List<OnboardingCard>) {
    var expandedIndex by remember { mutableIntStateOf(0) } // -1 means none expanded

    val activeTheme = if (expandedIndex >= 0) cards[expandedIndex] else null

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color((activeTheme?.startGradient ?: "#000000").toColorInt()),
                        Color((activeTheme?.endGradient ?: "#ffffff").toColorInt()),
                    )
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(3) { index ->
                OnboardingCard(
                    isExpanded = expandedIndex == index,
                    onCardClick = {
                        expandedIndex = if (expandedIndex == index) -1 else index
                    },
                    backgroundColor = Color((activeTheme?.backGroundColor ?: "#000000").toColorInt()).copy(0.3f),
                    strokeColors = listOf(
                        Color((activeTheme?.strokeStartColor ?: "#000000").toColorInt()),
                        Color((activeTheme?.strokeEndColor ?: "#ffffff").toColorInt()),
                    ),
                    onboardingCard = cards[index]
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}