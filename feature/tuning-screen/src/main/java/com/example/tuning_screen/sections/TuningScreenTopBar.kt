package com.example.tuning_screen.sections

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.design_system.theme.NekoViewIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TuningScreenTopBar(
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Настройки"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(NekoViewIcons.ArrowLeftFilled),
                    contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}