package com.shah.onboardinganimations.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun WelcomeText(
    firstLine: String,
    secondLine: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = firstLine,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            text = secondLine,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFFF8DC83)
        )
    }
}