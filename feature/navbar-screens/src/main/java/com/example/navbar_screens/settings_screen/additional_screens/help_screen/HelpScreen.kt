package com.example.navbar_screens.settings_screen.additional_screens.help_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.design_system.theme.mColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(
    navController: NavController
) {
    val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            HelpScreenTopBar(
                onNavIconClick = { navController.navigateUp() },
                scrollBehavior = topBarScrollBehavior
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(1000) {
                Text(it.toString())
            }
        }
    }
}