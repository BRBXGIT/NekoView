package com.example.navbar_screens.common

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ColorFilter
import com.example.design_system.theme.mColors
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun NavBar(
    onNavItemClick:(Int, Any) -> Unit,
    selectedItemIndex: Int
) {
    NavigationBar {
        navItems.forEachIndexed { index, navItem ->
            val isSelected = index == selectedItemIndex
            var animatedSelection by rememberSaveable { mutableStateOf(false) }

            //Change local state to start animation
            LaunchedEffect(isSelected) {
                when(isSelected) {
                    true -> {
                        animatedSelection = true
                    }
                    false -> {
                        animatedSelection = false
                        delay(800)
                    }
                }
            }

            val animatedImage = AnimatedImageVector.animatedVectorResource(navItem.icon)
            val animatedPainter = rememberAnimatedVectorPainter(animatedImageVector = animatedImage, atEnd = animatedSelection)

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if(!isSelected) {
                        onNavItemClick(index, navItem.destination)
                    }
                },
                icon = {
                    Image(
                        painter = animatedPainter,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(mColors.onSecondaryContainer)
                    )
                },
                label = {
                    Text(navItem.label)
                }
            )
        }
    }
}