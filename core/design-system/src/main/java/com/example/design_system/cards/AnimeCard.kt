package com.example.design_system.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@Composable
fun AnimeCard(
    index: Int, //To don't show AnimatedShimmer for next cards(it looks ugly)
    posterPath: String,
    genresString: String,
    title: String,
    onCardClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(170.dp, 270.dp)
            .clip(mShapes.small)
            .background(mColors.surfaceVariant)
            .clickable { onCardClick() } //TODO maybe have to rewrite to `combined clickable`
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(posterPath)
                .crossfade(500)
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            filterQuality = FilterQuality.Low,
            contentScale = ContentScale.Crop,
            loading = { if(index <= 6) AnimatedShimmer(100.dp, 130.dp) }
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(mColors.secondaryContainer)
                .padding(8.dp)
        ) {
            Text(
                text = title,
                style = mTypography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = genresString,
                style = mTypography.labelLarge.copy(
                    color = mColors.secondary
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

