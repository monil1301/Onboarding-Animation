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
import com.shah.onboardinganimations.data.model.OnboardingCard

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun OnboardingCardList(cards: List<OnboardingCard>) {
    var expandedIndex by remember { mutableIntStateOf(-1) } // -1 means none expanded

    val imageUrls = listOf(
        "https://img.myjar.app/RJf1PnhlAQ_5oZcgHo-g3I__7nIsAqFWoCj3-4dq_Hs/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/Homefeed/engagement_card/buyGoldEducation1.webp",
        "https://img.myjar.app/yXXRNLKGxWkoWRyyzurnZr3UJNHbqVaCHiLk1VYGlDM/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/Homefeed/engagement_card/buyGoldEducation2.webp",
        "https://img.myjar.app/fLqaeBj3sX4VtpjdRrj2_HxHPJ4YmqaFX9jPcm563cc/rs:fit:0:0/q:60/format:webp/plain/https://cdn.myjar.app/Homefeed/engagement_card/buyGoldEducation3.webp",
    )

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
                collapsedText = "Card ${index + 1}",
                expandedText = "Expanded Card ${index + 1}",
                imageUrl = imageUrls[index]
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}