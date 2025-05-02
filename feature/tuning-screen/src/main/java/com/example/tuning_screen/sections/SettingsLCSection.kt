package com.example.tuning_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.AppThemeVM
import com.example.design_system.theme.mTypography

@Composable
fun SettingsLCSection(
    appThemeVM: AppThemeVM,
    chosenTheme: String,
    chosenColorSystem: String
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Тема",
                    style = mTypography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )

                ThemePreviewsSection(
                    viewModel = appThemeVM,
                    chosenTheme = chosenTheme
                )

                ColorSystemElements(
                    chosenTheme = chosenTheme,
                    onColorSystemClick = { appThemeVM.changeColorSystem(it) },
                    chosenColorSystem = chosenColorSystem
                )
            }
        }
    }
}