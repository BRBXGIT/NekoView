package com.example.anime_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.anime_screen.sections.AnimeScreenTopBar
import com.example.anime_screen.sections.DescriptionSection
import com.example.anime_screen.sections.EpisodeItem
import com.example.anime_screen.sections.GenresLRSection
import com.example.anime_screen.sections.TitleHeader
import com.example.anime_screen.sections.TitleTeamSection
import com.example.anime_screen.sections.TorrentsSection
import com.example.design_system.cards.Utils
import com.example.design_system.snackbars.ObserveAsEvents
import com.example.design_system.snackbars.SnackbarController
import com.example.design_system.theme.mColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeScreen(
    navController: NavController,
    titleId: Int,
    viewModel: AnimeScreenVM
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

    //Analogue for viewModel init block
    val animeScreenState by viewModel.animeScreenState.collectAsStateWithLifecycle()
    LaunchedEffect(animeScreenState.title.id) {
        if(animeScreenState.title.id == 0) {
            viewModel.sendIntent(AnimeScreenIntent.FetchTitleDetails(titleId))
        }
    }

    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AnimeScreenTopBar(
                onBackClick = {  navController.navigateUp() },
                onHeartIconClick = {  },
                loadingState = animeScreenState.isLoading,
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
            modifier = Modifier.fillMaxSize()
        ) {
            if(!animeScreenState.isLoading) {
                val title = animeScreenState.title

                item {
                    TitleHeader(
                        name = title.names.ru,
                        nameEnglish = title.names.en,
                        season = "${title.season.year} ${title.season.string}",
                        type = title.type.fullString,
                        releaseState = title.status.string,
                        bannerImageUrl = Utils.BASE_POSTERS_URL + title.posters.original.url,
                        coverImageUrl = Utils.BASE_POSTERS_URL + title.posters.original.url,
                        topInnerPadding = innerPadding.calculateTopPadding() + 12.dp
                    )
                }

                item {
                    GenresLRSection(title.genres)
                }

                item {
                    TitleTeamSection(
                        voiceActors = title.team.voice.joinToString(", "),
                        timingWorkers = title.team.timing.joinToString(", "),
                        subtitlesWorkers = title.team.decor.joinToString(", ")
                    )
                }

                item {
                    DescriptionSection(title.description)
                }

                item {
                    TorrentsSection(title.torrents)
                }

                item {
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                }


                items(title.player.list.values.toList()) { episode ->
                    EpisodeItem(
                        episode = episode.episode,
                        name = episode.name ?: "Кажется названия ещё нет :)",
                        onWatchButtonClick = {}
                    )
                }
            }
        }
    }
}