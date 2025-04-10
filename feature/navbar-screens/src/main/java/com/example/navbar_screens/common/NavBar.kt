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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.design_system.theme.mColors

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun NavBar(
    navController: NavController
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val currentRoute = if(currentDestination != null) currentDestination.toString().split(".")[5] else "HomeScreenRoute"

    NavigationBar {
        navItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    if(currentRoute != navItem.route) {
                        navController.navigate(navItem.destination)
                    }
                },
                icon = {
                    val image = AnimatedImageVector.animatedVectorResource(navItem.icon)
                    Image(
                        colorFilter = ColorFilter.tint(mColors.onSecondaryContainer),
                        painter = rememberAnimatedVectorPainter(image, currentRoute == navItem.route),
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