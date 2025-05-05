package com.example.navbar_screens.search_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mShapes
import com.example.navbar_screens.search_screen.screen.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersBS(
    onDismissRequest: () -> Unit,
    genres: List<String>,
    years: List<Int>,
    seasons: List<String>,
    sort: SortType,
    releaseEnd: Boolean,
    onReleaseEndClick: () -> Unit,
    onSortClick: (SortType) -> Unit,
    selectedYears: List<Int>,
    onYearClick: (Int) -> Unit,
    onSeasonClick: (String) -> Unit,
    chosenSeasons: List<String>,
    onGenreClick: (String) -> Unit,
    chosenGenres: List<String>,
    yearsLoadState: Boolean,
    genresLoadState: Boolean,
    onApplyClick: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        shape = mShapes.small
    ) {
        Button(
            shape = mShapes.small,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = onApplyClick
        ) {
            Text(text = "Применить")
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(90.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filterSection(
                releaseEnd = releaseEnd,
                onReleaseEndClick = onReleaseEndClick
            )

            sortSection(
                sort = sort,
                onSortClick = { onSortClick(it) }
            )

            yearsSection(
                years = years,
                onYearClick = { onYearClick(it) },
                chosenYears = selectedYears,
                loadState = yearsLoadState
            )

            seasonSection(
                seasons = seasons,
                onSeasonClick = { onSeasonClick(it) },
                chosenSeasons = chosenSeasons
            )

            genresSection(
                genres = genres,
                onGenreClick = { onGenreClick(it) },
                chosenGenres = chosenGenres,
                loadState = genresLoadState
            )
        }
    }
}