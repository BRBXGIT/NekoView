package com.example.navbar_screens.home_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.anime_screen.navigation.AnimeScreenRoute
import com.example.common.CommonIntent
import com.example.common.CommonVM
import com.example.design_system.sections.EmptyContentSection
import com.example.design_system.snackbars.ObserveAsEvents
import com.example.design_system.snackbars.SnackbarAction
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.snackbars.SnackbarEvent
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mTypography
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

    var isError by rememberSaveable { mutableStateOf(false) }
    val titlesUpdates = viewModel.titlesUpdates.collectAsLazyPagingItems()
    LaunchedEffect(titlesUpdates.loadState.refresh, titlesUpdates.itemCount) {
        if(titlesUpdates.loadState.refresh is LoadState.Error) {
            isError = true
            SnackbarController.sendEvent(
                SnackbarEvent(
                    message = (titlesUpdates.loadState.refresh as LoadState.Error).error.message.toString(),
                    action = SnackbarAction(
                        name = "Retry",
                        action = { titlesUpdates.retry() }
                    )
                )
            )
        }

        if(titlesUpdates.itemCount != 0) {
            isError = false
        }
    }

    var isTitlesByQueryError by rememberSaveable { mutableStateOf(false) }
    val titlesByQuery = viewModel.animeByQuery.collectAsLazyPagingItems()
    LaunchedEffect(titlesByQuery.loadState.refresh, titlesByQuery.itemCount) {
        if(titlesByQuery.loadState.refresh is LoadState.Error) {
            isTitlesByQueryError = true
            SnackbarController.sendEvent(
                SnackbarEvent(
                    message = (titlesByQuery.loadState.refresh as LoadState.Error).error.message.toString(),
                    action = SnackbarAction(
                        name = "Retry",
                        action = { titlesUpdates.retry() }
                    )
                )
            )
        }

        if(titlesByQuery.itemCount != 0) {
            isTitlesByQueryError = false
        }
    }


    val homeScreenState by viewModel.homeScreenState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()
    val commonState by commonVM.commonState.collectAsStateWithLifecycle()
    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            HomeScreenTopBar(
                query = query,
                onSearchIconClick = { viewModel.sendIntent(HomeScreenIntent.ChangeSearchingMode) },
                scrollBehavior = topBarScrollBehavior,
                titlesUpdatesLoadingState = titlesUpdates.loadState.refresh is LoadState.Loading,
                isSearching = homeScreenState.isSearching,
                onSearchInput = { viewModel.sendIntent(HomeScreenIntent.SetQuery(it)) },
                titleByQueryLoadingState = titlesByQuery.loadState.refresh is LoadState.Loading,
                onClearButtonClick = { viewModel.sendIntent(HomeScreenIntent.SetQuery("")) }
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
            if(homeScreenState.isSearching) {
                if(isTitlesByQueryError) {
                    EmptyContentSection(
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    if(query == "") {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Кажется ещё ничего нет, начните вводить название тайтла :)",
                                textAlign = TextAlign.Center,
                                style = mTypography.titleMedium
                            )
                        }
                    } else {
                        TitlesUpdatesLVGSection(
                            titles = titlesByQuery,
                            onTitleClick = { navController.navigate(AnimeScreenRoute(it)) }
                        )
                    }
                }
            } else {
                if(isError) {
                    EmptyContentSection(
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    TitlesUpdatesLVGSection(
                        titles = titlesUpdates,
                        onTitleClick = { navController.navigate(AnimeScreenRoute(it)) }
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