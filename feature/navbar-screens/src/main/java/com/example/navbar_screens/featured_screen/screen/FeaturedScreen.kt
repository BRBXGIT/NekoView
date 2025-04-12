package com.example.navbar_screens.featured_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.common.AuthBS
import com.example.common.CommonIntent
import com.example.common.CommonVM
import com.example.design_system.snackbars.ObserveAsEvents
import com.example.design_system.snackbars.SnackbarAction
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.snackbars.SnackbarEvent
import com.example.design_system.theme.mColors
import com.example.navbar_screens.common.NavBar
import com.example.navbar_screens.common.NavRail
import com.example.navbar_screens.featured_screen.sections.FeatureScreenTopBar
import com.example.navbar_screens.featured_screen.sections.UserFeaturedLVGSection
import com.example.navbar_screens.featured_screen.sections.UserNotAuthorizedSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturedScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM,
    viewModel: FeaturedScreenVM
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

    val userFavorites = viewModel.userFavorites.collectAsLazyPagingItems()
    LaunchedEffect(userFavorites.loadState.refresh) {
        if(userFavorites.loadState.refresh is LoadState.Error) {
            SnackbarController.sendEvent(
                SnackbarEvent(
                    message = (userFavorites.loadState.refresh as LoadState.Error).error.message.toString(),
                    action = SnackbarAction(
                        name = "Retry",
                        action = { userFavorites.retry() }
                    )
                )
            )
        }
    }

    val commonState by commonVM.commonState.collectAsStateWithLifecycle()
    LaunchedEffect(commonState.sessionToken) {
        if(commonState.sessionToken != "") {
            viewModel.setUserSessionToken(commonState.sessionToken)
        }
    }
    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            FeatureScreenTopBar(
                onSearchClick = {  },
                scrollBehavior = topBarScrollBehavior,
                loadingState = userFavorites.loadState.refresh is LoadState.Loading
            )
        },
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
            var authBSOpened by rememberSaveable { mutableStateOf(false) }
            if(authBSOpened) {
                AuthBS(
                    onDismissRequest = { authBSOpened = false },
                    onAuthButtonClick = { email, password ->
                        commonVM.sendIntent(
                            CommonIntent.GetUserToken(email, password)
                        )
                        authBSOpened = false
                    }
                )
            }

            if(commonState.sessionToken.isNotEmpty()) {
                UserFeaturedLVGSection(userFavorites)
            } else {
                UserNotAuthorizedSection(
                    onAuthButtonClick = { authBSOpened = true }
                )
            }
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