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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.design_system.theme.mColors

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun NavRail(
    navController: NavController
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val currentRoute = if(currentDestination != null) currentDestination.toString().split(".")[5] else "HomeScreenRoute"

    NavigationRail(
        containerColor = mColors.surfaceContainer
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxHeight(),
        ) {
            navItems.forEach { navItem ->
                val chosen = currentRoute == navItem.route

                NavigationRailItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        if(!chosen) {
                            navController.navigate(navItem.destination)
                        }
                    },
                    icon = {
                        val image = AnimatedImageVector.animatedVectorResource(navItem.icon)
                        Image(
                            colorFilter = ColorFilter.tint(mColors.onSecondaryContainer),
                            painter = rememberAnimatedVectorPainter(image, chosen),
                            contentDescription = "Timer",
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