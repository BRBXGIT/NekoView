package com.example.tuning_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.common.CommonIntent
import com.example.common.CommonVM
import com.example.design_system.theme.AppThemeVM
import com.example.tuning_screen.screen.TuningScreen
import kotlinx.serialization.Serializable

@Serializable
data object TuningScreenRoute

fun NavGraphBuilder.tuningScreen(
    navController: NavController,
    appThemeVM: AppThemeVM,
    commonVM: CommonVM
) = composable<TuningScreenRoute> {
    commonVM.sendIntent(CommonIntent.FetchAutoPlay)
    commonVM.sendIntent(CommonIntent.FetchVideoQuality)
    commonVM.sendIntent(CommonIntent.FetchShowSkipOpeningButton)
    commonVM.sendIntent(CommonIntent.FetchAutoSkipOpening)

    TuningScreen(
        navController = navController,
        appThemeVM = appThemeVM,
        commonVM = commonVM
    )
}