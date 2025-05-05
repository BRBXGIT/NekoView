package com.example.navbar_screens.search_screen.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mTypography
import com.example.navbar_screens.search_screen.screen.SortType

fun LazyGridScope.sortSection(
    sort: SortType,
    onSortClick: (SortType) -> Unit
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = "Сортировка",
            style = mTypography.titleMedium
        )
    }

    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                RadioButton(
                    selected = sort == SortType.ByPopularity,
                    onClick = { onSortClick(SortType.ByPopularity) }
                )

                Text(
                    text = "По популярности",
                    style = mTypography.bodyLarge
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            )  {
                RadioButton(
                    selected = sort == SortType.ByNovelty,
                    onClick = { onSortClick(SortType.ByNovelty) }
                )

                Text(
                    text = "По новизне",
                    style = mTypography.bodyLarge
                )
            }
        }
    }
}