package com.shah.onboardinganimations.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shah.onboardinganimations.ui.components.TopBar

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun LandingScreen(onBackClick: () -> Unit) {
    Scaffold(
        Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            TopBar(
                "https://cdn-icons-png.flaticon.com/512/271/271220.png",
                "Landing Page",
                onBackClick
            )
        },
        containerColor = Color(0xFF272239)
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Landing Page",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = Color.White
            )
        }
    }
}
