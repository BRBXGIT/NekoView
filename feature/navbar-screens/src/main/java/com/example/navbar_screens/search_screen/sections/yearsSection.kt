package com.example.navbar_screens.search_screen.sections

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

fun LazyGridScope.yearsSection(
    years: List<Int>,
    onYearClick: (Int) -> Unit,
    chosenYears: List<Int>
) {
    item(
        span = { GridItemSpan(maxLineSpan) }
    ) {
        Text(
            text = "Год",
            style = mTypography.titleMedium
        )
    }

    items(years) { year ->
        val animatedColor by animateColorAsState(
            targetValue = if(year in chosenYears) mColors.primary else mColors.surfaceContainerHigh,
            animationSpec = tween(300)
        )

        Surface(
            color = animatedColor,
            shape = mShapes.small,
            onClick = { onYearClick(year) }
        ) {
            Text(
                text = year.toString(),
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}