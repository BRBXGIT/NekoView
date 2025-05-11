package com.example.navbar_screens.settings_screen.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuitDialog(
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.background(
            color = mColors.surfaceContainerHigh,
            shape = mShapes.small
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Выход",
                style = mTypography.bodyLarge,
                color = mColors.onSurface,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Вы уверены что хотите выйти из аккаунта?",
                color = mColors.onSurface,
                style = mTypography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(text = "Отмена")
                }

                TextButton(
                    onClick = {
                        onDismissRequest()
                        onConfirmClick()
                    }
                ) {
                    Text(text = "Выйти")
                }
            }
        }
    }
}