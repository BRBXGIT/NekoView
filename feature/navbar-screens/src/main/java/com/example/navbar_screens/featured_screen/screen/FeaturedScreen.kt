package com.example.navbar_screens.featured_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.CommonVM
import com.example.design_system.theme.mColors
import com.example.navbar_screens.common.NavBar
import com.example.navbar_screens.common.NavRail
import androidx.compose.runtime.getValue
import com.example.common.CommonIntent

@Composable
fun FeaturedScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM
) {
    val commonState by commonVM.commonState.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            if(!bigScreen) {
                NavBar(
                    selectedItemIndex = commonState.selectedNavIndex,
                    onNavItemClick = { index, destination ->
                        commonVM.sendIntent(
                            CommonIntent.SetNavIndex(index)
                        )
                        navController.navigate(destination)
                    }
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .then(
                if(bigScreen) {
                    Modifier.padding(start = 80.dp)
                } else {
                    Modifier.padding(start = 0.dp)
                }
            )
            .background(mColors.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }

    if(bigScreen) {
        NavRail(
            selectedItemIndex = commonState.selectedNavIndex,
            onNavItemClick = { index, destination ->
                commonVM.sendIntent(
                    CommonIntent.SetNavIndex(index)
                )
                navController.navigate(destination)
            }
        )
    }
}