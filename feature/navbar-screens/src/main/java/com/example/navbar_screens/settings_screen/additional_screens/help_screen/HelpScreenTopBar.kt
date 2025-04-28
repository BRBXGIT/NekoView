package com.example.navbar_screens.settings_screen.additional_screens.help_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.design_system.theme.NekoViewIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreenTopBar(
    onNavIconClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = "AniLibria - творческое объединение занимающееся озвучиванием аниме"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavIconClick
            ) {
                Icon(
                    painter = painterResource(NekoViewIcons.ArrowLeftFilled),
                    contentDescription = null
                )
            }
        }
    )
}