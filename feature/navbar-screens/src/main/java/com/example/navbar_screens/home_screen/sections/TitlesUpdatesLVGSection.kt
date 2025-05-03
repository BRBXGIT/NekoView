package com.example.navbar_screens.home_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.data.remote.models.titles_list_response.Item1
import com.example.design_system.cards.AnimeCard
import com.example.design_system.cards.DesignUtils
import com.example.design_system.theme.mShapes

@Composable
fun TitlesUpdatesLVGSection(
    titles: LazyPagingItems<Item1>,
    onTitleClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 16.dp
        )
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = mShapes.small,
                onClick = {  }
            ) {
                Text(
                    text = "Случайный релиз"
                )
            }
        }

        items(titles.itemCount) { index ->
            val currentTitle = titles[index]

            currentTitle?.let {
                AnimeCard(
                    posterPath = DesignUtils.BASE_POSTERS_URL + currentTitle.posters.small.url,
                    genresString = currentTitle.genres.joinToString(", "),
                    title = currentTitle.names.ru,
                    onCardClick = { onTitleClick(currentTitle.id) }
                )
            }
        }
    }
}