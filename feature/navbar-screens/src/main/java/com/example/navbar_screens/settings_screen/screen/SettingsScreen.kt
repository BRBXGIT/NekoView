package com.example.navbar_screens.settings_screen.screen

import android.content.Intent
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.CommonIntent
import com.example.common.CommonVM
import com.example.design_system.cards.DesignUtils
import com.example.design_system.snackbars.ObserveAsEvents
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.theme.mColors
import com.example.navbar_screens.common.NavBar
import com.example.navbar_screens.common.NavRail
import com.example.navbar_screens.settings_screen.sections.QuitDialog
import com.example.navbar_screens.settings_screen.sections.SettingsItemsLCSection
import com.example.navbar_screens.settings_screen.sections.SettingsScreenTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    bigScreen: Boolean,
    commonVM: CommonVM,
    viewModel: SettingsScreenVM
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
    val settingsScreenState by viewModel.settingsScreenState.collectAsStateWithLifecycle()
    LaunchedEffect(commonState.sessionToken, settingsScreenState) {
        if((commonState.sessionToken != "") and (settingsScreenState == SettingsScreenState())) {
            viewModel.sendIntent(SettingsScreenIntent.LoadUserDetails(commonState.sessionToken))
        }
    }

    var quitDialogOpen by rememberSaveable { mutableStateOf(false) }
    if(quitDialogOpen) {
        QuitDialog(
            onConfirmClick = { commonVM.sendIntent(CommonIntent.ChangeSessionToken("")) },
            onDismissRequest = { quitDialogOpen = false }
        )
    }
    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            SettingsScreenTopBar(
                userName = settingsScreenState.userName,
                userImageUrl = DesignUtils.BASE_POSTERS_URL + settingsScreenState.userImageUrl,
                loadingState = settingsScreenState.userDetailsLoading,
                onExitClick = { quitDialogOpen = true },
                scrollBehavior = topBarScrollBehavior
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
        val context = LocalContext.current

        SettingsItemsLCSection(
            innerPadding = innerPadding,
            onSettingsItemClick = { navController.navigate(it) },
            onLinkItemClick = { link ->
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        link.toUri()
                    )
                )
            }
        )
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