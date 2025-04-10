package com.example.navbar_screens.common

import com.example.design_system.theme.NekoViewIcons
import com.example.navbar_screens.featured_screen.navigation.FeaturedScreenRoute
import com.example.navbar_screens.home_screen.navigation.HomeScreenRoute
import com.example.navbar_screens.settings_screen.navigation.SettingsScreenRoute

data class NavItem(
    val label: String,
    val icon: Int, //Icon already animated from filled to outlined
    val destination: Any
)

val navItems = listOf(
    NavItem(
        label = "Аниме",
        icon = NekoViewIcons.HomeAnimated,
        destination = HomeScreenRoute
    ),
    NavItem(
        label = "Избранное",
        icon = NekoViewIcons.HeartAnimated,
        destination = FeaturedScreenRoute
    ),
    NavItem(
        label = "Настройки",
        icon = NekoViewIcons.SettingsAnimated,
        destination = SettingsScreenRoute
    )
)