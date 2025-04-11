package com.example.navbar_screens.home_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.CommonIntent
import com.example.common.CommonVM
import com.example.design_system.snackbars.ObserveAsEvents
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.theme.mColors
import com.example.navbar_screens.common.NavBar
import com.example.navbar_screens.common.NavRail
import com.example.navbar_screens.home_screen.sections.HomeScreenTopBar
import com.example.navbar_screens.home_screen.sections.TitlesUpdatesLVGSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenVM,
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM
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

    val commonState by commonVM.commonState.collectAsStateWithLifecycle()
    val homeScreenState by viewModel.homeScreenState.collectAsStateWithLifecycle()
    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            HomeScreenTopBar(
                onSearchClick = {},
                scrollBehavior = topBarScrollBehavior,
                loadingState = homeScreenState.isLoading
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            //Check if user cant scroll forward load items
            val state = rememberLazyGridState()
            LaunchedEffect(state) {
                snapshotFlow { state.canScrollForward }
                    .collect { canScrollForward ->
                        if(!canScrollForward) {
                            viewModel.sendIntent(HomeScreenIntent.LoadTitles)
                        }
                    }
            }

            TitlesUpdatesLVGSection(
                state = state,
                titles = homeScreenState.titlesUpdates
            )
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