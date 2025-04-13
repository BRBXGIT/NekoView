package com.example.anime_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.design_system.theme.mColors
import androidx.compose.runtime.getValue
import com.example.data.remote.models.title_details_response.TitleDetailsResponse

@Composable
fun AnimeScreen(
    navController: NavController,
    titleId: Int,
    viewModel: AnimeScreenVM
) {
    //Analogue for viewModel init block
    val animeScreenState by viewModel.animeScreenState.collectAsStateWithLifecycle()
    LaunchedEffect(animeScreenState.title) {
        if(animeScreenState.title == TitleDetailsResponse()) {
            viewModel.sendIntent(AnimeScreenIntent.FetchTitleDetails(titleId))
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = titleId.toString())
        }
    }
}