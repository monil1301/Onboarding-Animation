package com.shah.onboardinganimations.data.model

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingCard(
    val backGroundColor: String,
    val collapsedStateText: String,
    val endGradient: String,
    val expandStateText: String,
    val image: String,
    val startGradient: String,
    val strokeEndColor: String,
    val strokeStartColor: String
)