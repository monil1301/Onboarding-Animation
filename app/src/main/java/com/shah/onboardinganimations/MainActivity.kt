package com.shah.onboardinganimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.shah.onboardinganimations.ui.screens.LandingScreen
import com.shah.onboardinganimations.ui.screens.OnboardingScreen
import com.shah.onboardinganimations.ui.theme.OnboardingAnimationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingAnimationsTheme {
                var isOnboardingCompleted by remember { mutableStateOf(false) }
                OnboardingAnimationsTheme {
                    if (!isOnboardingCompleted)
                        OnboardingScreen {
                            isOnboardingCompleted = true
                        }
                    else
                        LandingScreen {
                            isOnboardingCompleted = false
                        }
                }
            }
        }
    }
}
