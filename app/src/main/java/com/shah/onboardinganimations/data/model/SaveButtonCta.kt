package com.shah.onboardinganimations.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SaveButtonCta(
    val backgroundColor: String,
    val strokeColor: String,
    val text: String,
    val textColor: String
)