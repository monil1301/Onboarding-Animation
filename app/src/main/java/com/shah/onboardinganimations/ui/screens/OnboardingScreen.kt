package com.shah.onboardinganimations.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.shah.onboardinganimations.ui.components.OnboardingCardList
import com.shah.onboardinganimations.ui.components.TopBar
import com.shah.onboardinganimations.ui.viewmodel.OnboardingViewModel
import com.shah.onboardinganimations.utils.ResponseResource

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel = hiltViewModel(), onNext: () -> Unit) {
    val state = viewModel.onboardingResult.collectAsState()

    viewModel.fetchOnboarding()

    Scaffold(
        modifier = Modifier.fillMaxSize().systemBarsPadding()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (state.value) {
                is ResponseResource.Success -> {
                    val onboardingDetails =
                        (state.value as ResponseResource.Success).data.onboardingData.manualBuyEducationData
                    val cards = onboardingDetails.educationCardList
                    OnboardingCardList(
                        cards,
                        onboardingDetails.expandCardStayInterval,
                        onboardingDetails.bottomToCenterTranslationInterval,
                        onboardingDetails.collapseExpandIntroInterval,
                        onboardingDetails.saveButtonCta,
                        onboardingDetails.toolBarIcon,
                        onboardingDetails.toolBarText,
                        onNext
                    )
                }

                is ResponseResource.Failure -> {
                    Text(
                        text = (state.value as ResponseResource.Failure).errorMessage,
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Red
                    )
                }

                null -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                ResponseResource.Idle -> {}
                ResponseResource.Loading -> {}
            }
        }
    }
}
