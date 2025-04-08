package com.example.design_system.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val lightLavenderScheme = lightColorScheme(
    primary = primaryLavenderLight,
    onPrimary = onPrimaryLavenderLight,
    primaryContainer = primaryContainerLavenderLight,
    onPrimaryContainer = onPrimaryContainerLavenderLight,
    secondary = secondaryLavenderLight,
    onSecondary = onSecondaryLavenderLight,
    secondaryContainer = secondaryContainerLavenderLight,
    onSecondaryContainer = onSecondaryContainerLavenderLight,
    tertiary = tertiaryLavenderLight,
    onTertiary = onTertiaryLavenderLight,
    tertiaryContainer = tertiaryContainerLavenderLight,
    onTertiaryContainer = onTertiaryContainerLavenderLight,
    error = errorLavenderLight,
    onError = onErrorLavenderLight,
    errorContainer = errorContainerLavenderLight,
    onErrorContainer = onErrorContainerLavenderLight,
    background = backgroundLavenderLight,
    onBackground = onBackgroundLavenderLight,
    surface = surfaceLavenderLight,
    onSurface = onSurfaceLavenderLight,
    surfaceVariant = surfaceVariantLavenderLight,
    onSurfaceVariant = onSurfaceVariantLavenderLight,
    outline = outlineLavenderLight,
    outlineVariant = outlineVariantLavenderLight,
    scrim = scrimLavenderLight,
    inverseSurface = inverseSurfaceLavenderLight,
    inverseOnSurface = inverseOnSurfaceLavenderLight,
    inversePrimary = inversePrimaryLavenderLight,
    surfaceDim = surfaceDimLavenderLight,
    surfaceBright = surfaceBrightLavenderLight,
    surfaceContainerLowest = surfaceContainerLowestLavenderLight,
    surfaceContainerLow = surfaceContainerLowLavenderLight,
    surfaceContainer = surfaceContainerLavenderLight,
    surfaceContainerHigh = surfaceContainerHighLavenderLight,
    surfaceContainerHighest = surfaceContainerHighestLavenderLight,
)

val darkLavenderScheme = darkColorScheme(
    primary = primaryLavenderDark,
    onPrimary = onPrimaryLavenderDark,
    primaryContainer = primaryContainerLavenderDark,
    onPrimaryContainer = onPrimaryContainerLavenderDark,
    secondary = secondaryLavenderDark,
    onSecondary = onSecondaryLavenderDark,
    secondaryContainer = secondaryContainerLavenderDark,
    onSecondaryContainer = onSecondaryContainerLavenderDark,
    tertiary = tertiaryLavenderDark,
    onTertiary = onTertiaryLavenderDark,
    tertiaryContainer = tertiaryContainerLavenderDark,
    onTertiaryContainer = onTertiaryContainerLavenderDark,
    error = errorLavenderDark,
    onError = onErrorLavenderDark,
    errorContainer = errorContainerLavenderDark,
    onErrorContainer = onErrorContainerLavenderDark,
    background = backgroundLavenderDark,
    onBackground = onBackgroundLavenderDark,
    surface = surfaceLavenderDark,
    onSurface = onSurfaceLavenderDark,
    surfaceVariant = surfaceVariantLavenderDark,
    onSurfaceVariant = onSurfaceVariantLavenderDark,
    outline = outlineLavenderDark,
    outlineVariant = outlineVariantLavenderDark,
    scrim = scrimLavenderDark,
    inverseSurface = inverseSurfaceLavenderDark,
    inverseOnSurface = inverseOnSurfaceLavenderDark,
    inversePrimary = inversePrimaryLavenderDark,
    surfaceDim = surfaceDimLavenderDark,
    surfaceBright = surfaceBrightLavenderDark,
    surfaceContainerLowest = surfaceContainerLowestLavenderDark,
    surfaceContainerLow = surfaceContainerLowLavenderDark,
    surfaceContainer = surfaceContainerLavenderDark,
    surfaceContainerHigh = surfaceContainerHighLavenderDark,
    surfaceContainerHighest = surfaceContainerHighestLavenderDark,
)

@Composable
fun NekoViewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkLavenderScheme
        else -> lightLavenderScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}