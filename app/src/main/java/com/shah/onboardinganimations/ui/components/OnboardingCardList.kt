package com.shah.onboardinganimations.ui.components

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
import androidx.compose.ui.unit.dp

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun OnboardingCardList() {
    var expandedIndex by remember { mutableIntStateOf(-1) } // -1 means none expanded

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
                title = "Card ${index + 1}"
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}