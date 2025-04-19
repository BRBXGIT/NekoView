package com.example.anime_screen.player_screen.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.anime_screen.common.AnimeScreenVM
import androidx.compose.runtime.getValue

@Composable
fun PlayerScreen(
    viewModel: AnimeScreenVM
) {
    val titleState by viewModel.animeScreenState.collectAsStateWithLifecycle()


}