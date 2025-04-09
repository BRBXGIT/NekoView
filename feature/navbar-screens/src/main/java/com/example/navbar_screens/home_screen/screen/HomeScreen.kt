package com.example.navbar_screens.home_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.design_system.snackbars.ObserveAsEvents
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.theme.mColors
import com.example.navbar_screens.common.NavBar
import com.example.navbar_screens.common.NavRail
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeScreenVM,
    navController: NavController,
    bigScreen: Boolean
) {
    //Snackbars stuff
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    ObserveAsEvents(flow = SnackbarController.events, snackbarHostState) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.name,
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true
            )

            if(result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    val homeScreenState by viewModel.homeScreenState.collectAsStateWithLifecycle()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            if(!bigScreen) {
                NavBar(navController)
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
            Text(homeScreenState.titlesUpdates.toString())
        }
    }

    if(bigScreen) {
        NavRail(navController)
    }
}