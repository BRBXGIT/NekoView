package com.example.nekoview

import android.app.PictureInPictureParams
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anime_screen.player_screen.sections.videoViewBounds
import com.example.design_system.theme.AppThemeVM
import com.example.design_system.theme.NekoViewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val isPipSupported by lazy {
        packageManager.hasSystemFeature(
            PackageManager.FEATURE_PICTURE_IN_PICTURE
        )
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appThemeVM = hiltViewModel<AppThemeVM>()

            val theme by appThemeVM.theme.collectAsState(initial = "default")
            val colorSystem by appThemeVM.colorSystem.collectAsState(initial = "default")

            NekoViewTheme(
                colorSystem = colorSystem,
                theme = theme
            ) {
                val windowSize = calculateWindowSizeClass(this)
                val bigScreen = windowSize.widthSizeClass != WindowWidthSizeClass.Compact

                NavGraph(bigScreen)
            }
        }
    }

    private fun updatedPipParams(): PictureInPictureParams {
        return PictureInPictureParams.Builder()
            .setSourceRectHint(videoViewBounds)
            .setAspectRatio(Rational(16, 9))
            .build()
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if(!isPipSupported) {
            return
        }

        enterPictureInPictureMode(
            updatedPipParams()
        )
    }
}