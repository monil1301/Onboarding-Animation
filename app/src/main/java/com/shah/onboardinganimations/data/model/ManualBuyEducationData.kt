package com.shah.onboardinganimations.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ManualBuyEducationData(
    val actionText: String,
    val bottomToCenterTranslationInterval: Int,
    val cohort: String,
    val collapseCardTiltInterval: Int,
    val collapseExpandIntroInterval: Int,
    val ctaLottie: String,
    val educationCardList: List<OnboardingCard>,
    val expandCardStayInterval: Int,
    val introSubtitle: String,
    val introSubtitleIcon: String,
    val introTitle: String,
    val saveButtonCta: SaveButtonCta,
    val screenType: String,
    val shouldShowBeforeNavigating: Boolean,
    val shouldShowOnLandingPage: Boolean,
    val toolBarIcon: String,
    val toolBarText: String
)