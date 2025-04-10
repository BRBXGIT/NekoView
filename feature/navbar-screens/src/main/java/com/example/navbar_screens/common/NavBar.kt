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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.example.design_system.theme.mColors

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun NavBar(
    onNavItemClick:(Int, Any) -> Unit,
    selectedItemIndex: Int
) {
    NavigationBar {
        navItems.forEachIndexed { index, navItem ->
            val selected = index == selectedItemIndex

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if(!selected) {
                        onNavItemClick(index, navItem.destination)
                    }
                },
                icon = {
                    val image = AnimatedImageVector.animatedVectorResource(navItem.icon)
                    Image(
                        colorFilter = ColorFilter.tint(mColors.onSecondaryContainer),
                        painter = rememberAnimatedVectorPainter(image, selected),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                },
                label = {
                    Text(navItem.label)
                }
            )
        }
    }
}