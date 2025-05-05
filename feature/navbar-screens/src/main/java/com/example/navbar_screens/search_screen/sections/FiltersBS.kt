package com.example.navbar_screens.search_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mShapes
import com.example.navbar_screens.home_screen.sections.filterSection
import com.example.navbar_screens.home_screen.sections.sortSection
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
    onYearClick: (Int) -> Unit
) {
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = onDismissRequest,
        shape = mShapes.small
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(60.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
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
                chosenYears = selectedYears
            )
        }
    }
}