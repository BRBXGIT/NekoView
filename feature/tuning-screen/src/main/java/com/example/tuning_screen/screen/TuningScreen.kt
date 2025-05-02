package com.example.tuning_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.design_system.theme.AppThemeVM
import com.example.design_system.theme.mColors
import com.example.tuning_screen.sections.SettingsLCSection
import com.example.tuning_screen.sections.TuningScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TuningScreen(
    navController: NavController,
    viewModel: TuningScreenVM,
    appThemeVM: AppThemeVM
) {
    val videoQuality by viewModel.videoQuality.collectAsStateWithLifecycle()
    val autoPlay by viewModel.autoPlay.collectAsStateWithLifecycle()
    val autoSkipOpening by viewModel.autoSkipOpening.collectAsStateWithLifecycle()
    val showSkipOpeningButton by viewModel.showSkipOpeningButton.collectAsStateWithLifecycle()

    val chosenTheme by appThemeVM.theme.collectAsState(initial = "default")
    val chosenColorSystem by appThemeVM.colorSystem.collectAsState(initial = "default")

    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            TuningScreenTopBar(
                onBackClick = { navController.navigateUp() },
                scrollBehavior = topBarScrollBehavior
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SettingsLCSection(
                appThemeVM = appThemeVM,
                chosenTheme = chosenTheme
            )
        }
    }
}