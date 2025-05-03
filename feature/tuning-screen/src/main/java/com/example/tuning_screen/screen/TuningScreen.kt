package com.example.tuning_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.CommonIntent
import com.example.common.CommonVM
import com.example.design_system.theme.AppThemeVM
import com.example.design_system.theme.mColors
import com.example.tuning_screen.sections.PlayerSettingsItemType
import com.example.tuning_screen.sections.SettingsLCSection
import com.example.tuning_screen.sections.TuningScreenTopBar
import com.example.tuning_screen.sections.VideoQualityBS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TuningScreen(
    navController: NavController,
    appThemeVM: AppThemeVM,
    commonVM: CommonVM
) {
    val chosenTheme by appThemeVM.theme.collectAsState(initial = "default")
    val chosenColorSystem by appThemeVM.colorSystem.collectAsState(initial = "default")

    val videoQuality by commonVM.videoQuality.collectAsStateWithLifecycle()
    val autoSkipOpening by commonVM.autoSkipOpening.collectAsStateWithLifecycle()
    val showSkipOpeningButton by commonVM.showSkipOpeningButton.collectAsStateWithLifecycle()
    val autoPlay by commonVM.autoPlay.collectAsStateWithLifecycle()

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
        var videoQualityBSOpen by rememberSaveable { mutableStateOf(false) }
        if(videoQualityBSOpen) {
            VideoQualityBS(
                onDismissRequest = { videoQualityBSOpen = false },
                onSetQualityClick = { commonVM.sendIntent(CommonIntent.ChangeVideoQuality(it)) }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SettingsLCSection(
                appThemeVM = appThemeVM,
                chosenTheme = chosenTheme,
                chosenColorSystem = chosenColorSystem,
                videoQuality = videoQuality,
                autoPlay = autoPlay,
                showSkipOpeningButton = showSkipOpeningButton,
                autoSkipOpening = autoSkipOpening,
                onCheckChange = {
                    when(it) {
                        PlayerSettingsItemType.VideoQuality -> {
                            videoQualityBSOpen = true
                        }
                        PlayerSettingsItemType.ShowSkipOpeningButton -> {
                            commonVM.sendIntent(CommonIntent.ChangeShowSkipOpeningButton)
                        }
                        PlayerSettingsItemType.AutoSkipOpening -> {
                            commonVM.sendIntent(CommonIntent.ChangeAutoSkipOpening)
                        }
                        PlayerSettingsItemType.AutoPlay -> {
                            commonVM.sendIntent(CommonIntent.ChangeAutoplay)
                        }
                    }
                },
            )
        }
    }
}