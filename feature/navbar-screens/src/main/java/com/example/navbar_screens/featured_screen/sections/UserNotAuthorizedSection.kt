package com.example.navbar_screens.featured_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mTypography

@Composable
fun UserNotAuthorizedSection(
    onAuthButtonClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Что-бы просматривать избранное и добавлять туда тайтлы нужно авторизоваться :)",
                style = mTypography.bodyLarge,
                textAlign = TextAlign.Center
            )

            OutlinedButton(
                onClick = onAuthButtonClick
            ) {
                Text(text = "Авторизоваться")
            }
        }
    }
}