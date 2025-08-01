package com.shah.onboardinganimations.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.shah.onboardinganimations.data.model.SaveButtonCta

/**
 * Created by Monil on 01/08/25.
 */

@Composable
fun SaveGoldButton(
    modifier: Modifier,
    saveButtonCta: SaveButtonCta, onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(saveButtonCta.backgroundColor.toColorInt()),
                contentColor = Color(saveButtonCta.textColor.toColorInt())
            ),
            shape = RoundedCornerShape(31.dp),
            border = BorderStroke(
                1.dp,
                Color(saveButtonCta.strokeColor.toColorInt())
            )
        ) {
            Text(
                text = saveButtonCta.text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
