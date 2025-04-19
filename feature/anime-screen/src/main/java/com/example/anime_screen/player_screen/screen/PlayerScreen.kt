package com.example.anime_screen.player_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.anime_screen.common.AnimeScreenVM
import com.example.anime_screen.player_screen.sections.AnimePlayer
import com.example.design_system.theme.mColors

@Composable
fun PlayerScreen(
    viewModel: AnimeScreenVM,
    selectedEpisodeIndex: Int,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
    ) {
        val animeScreenState by viewModel.animeScreenState.collectAsStateWithLifecycle()
        var index by rememberSaveable { mutableIntStateOf(selectedEpisodeIndex) }

        var selectedEpisodeLink = "https://" +
                animeScreenState.title.player.host +
                animeScreenState.title.player.list.values.toList()[index].hls.fhd
        AnimePlayer(
            episodeUrl = selectedEpisodeLink
        )
    }
}