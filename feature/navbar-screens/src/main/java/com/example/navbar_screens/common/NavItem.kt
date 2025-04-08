package com.example.navbar_screens.common

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