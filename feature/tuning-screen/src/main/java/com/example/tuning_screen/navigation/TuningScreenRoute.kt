package com.example.tuning_screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
) = composable<TuningScreenRoute>(
    enterTransition = { fadeIn(tween(400)) },
    exitTransition = { fadeOut(tween(400)) }
) {

    commonVM.sendIntent(CommonIntent.FetchVideoQuality)
    commonVM.sendIntent(CommonIntent.FetchAutoSkipOpening)
    commonVM.sendIntent(CommonIntent.FetchShowSkipOpeningButton)
    commonVM.sendIntent(CommonIntent.FetchAutoPlay)

    TuningScreen(
        navController = navController,
        appThemeVM = appThemeVM,
        commonVM = commonVM
    )
}