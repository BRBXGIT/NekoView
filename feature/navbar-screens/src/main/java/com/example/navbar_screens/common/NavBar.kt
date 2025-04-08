package com.example.navbar_screens.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.design_system.theme.NekoViewIcons
import com.example.navbar_screens.featured_screen.navigation.FeaturedScreenRoute
import com.example.navbar_screens.home_screen.navigation.HomeScreenRoute
import com.example.navbar_screens.settings_screen.navigation.SettingsScreenRoute

data class NavItem(
    val label: String,
    val iconDefault: Int,
    val iconChosen: Int,
    val route: String,
    val destination: Any
)

@Composable
fun NavBar(
    navController: NavController
) {
    val navItems = listOf(
        NavItem(
            label = "Аниме",
            iconDefault = NekoViewIcons.Home,
            iconChosen = NekoViewIcons.HomeFilled,
            route = "HomeScreenRoute",
            destination = HomeScreenRoute
        ),
        NavItem(
            label = "Избранное",
            iconDefault = NekoViewIcons.Star,
            iconChosen = NekoViewIcons.StarFilled,
            route = "FeaturedScreenRoute",
            destination = FeaturedScreenRoute
        ),
        NavItem(
            label = "Настройки",
            iconDefault = NekoViewIcons.Settings,
            iconChosen = NekoViewIcons.SettingsFilled,
            route = "SettingsScreenRoute",
            destination = SettingsScreenRoute
        )
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val currentRoute = if(currentDestination != null) currentDestination.toString().split(".")[5] else "HomeScreenRoute"

    NavigationBar {
        navItems.forEach { navItem ->
            val chosen = currentRoute == navItem.route

            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    if(!chosen) {
                        navController.navigate(navItem.destination)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = if(chosen) navItem.iconChosen else navItem.iconDefault),
                        contentDescription = null
                    )
                },
                label = {
                    Text(navItem.label)
                }
            )
        }
    }
}