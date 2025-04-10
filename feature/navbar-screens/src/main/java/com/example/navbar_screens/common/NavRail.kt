package com.example.navbar_screens.common

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mColors

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun NavRail(
    onNavItemClick:(Int, Any) -> Unit,
    selectedItemIndex: Int
) {
    NavigationRail(
        containerColor = mColors.surfaceContainer
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxHeight(),
        ) {
            navItems.forEachIndexed { index, navItem ->
                val selected = index == selectedItemIndex

                NavigationRailItem(
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
}