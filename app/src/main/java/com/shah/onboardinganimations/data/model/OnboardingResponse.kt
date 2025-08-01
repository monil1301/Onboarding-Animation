package com.shah.onboardinganimations.data.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingResponse(
    @Json(name = "data") val onboardingData: OnboardingData,
    val success: Boolean
)