package com.example.navbar_screens.featured_screen.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.AuthBS
import com.example.common.CommonIntent
import com.example.common.CommonVM
import com.example.design_system.theme.mColors
import com.example.navbar_screens.common.NavBar
import com.example.navbar_screens.common.NavRail
import com.example.navbar_screens.featured_screen.sections.UserFeaturedLVGSection
import com.example.navbar_screens.featured_screen.sections.UserNotAuthorizedSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturedScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM,
    viewModel: FeaturedScreenVM
) {
    val commonState by commonVM.commonState.collectAsStateWithLifecycle()
    val featureScreenState by viewModel.featuredScreenState.collectAsStateWithLifecycle()

    //Replacement for init block in viewModel, cause i don't know how i can throw session token to it
    LaunchedEffect(commonState.sessionToken) {
        if((commonState.sessionToken.isNotEmpty()) and (featureScreenState.userFeaturedTitles.isEmpty())) {
            viewModel.sendIntent(
                FeatureScreenIntent.LoadUserFeaturedTitles(commonState.sessionToken)
            )
        }
    }

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
                //Check if user can't scroll forward load items
                val state = rememberLazyGridState()
                LaunchedEffect(state) {
                    snapshotFlow { state.canScrollForward }
                        .collect { canScrollForward ->
                            if(!canScrollForward) {
                                viewModel.sendIntent(
                                    FeatureScreenIntent.LoadUserFeaturedTitles(commonState.sessionToken)
                                )
                            }
                        }
                }

                UserFeaturedLVGSection(
                    state = state,
                    titles = featureScreenState.userFeaturedTitles
                )
            } else {
                PullToRefreshBox(
                    isRefreshing = commonState.isSessionTokenLoading,
                    onRefresh = {  }
                ) {
                    UserNotAuthorizedSection(
                        onAuthButtonClick = { authBSOpened = true }
                    )
                }
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