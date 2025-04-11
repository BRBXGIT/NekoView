package com.example.navbar_screens.featured_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.remote.models.titles_list_response.Item1
import com.example.design_system.cards.AnimeCard
import com.example.design_system.cards.Utils

@Composable
fun UserFeaturedLVGSection(
    state: LazyGridState,
    titles: List<Item1>
) {
    LazyVerticalGrid(
        state = state,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(170.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 16.dp
        )
    ) {
        itemsIndexed(titles) { index, title ->
            AnimeCard(
                index = index,
                posterPath = Utils.BASE_POSTERS_URL + title.posters.small.url,
                genresString = title.genres.joinToString(", "),
                title = title.names.ru,
                onCardClick = {  }
            )
        }
    }
}