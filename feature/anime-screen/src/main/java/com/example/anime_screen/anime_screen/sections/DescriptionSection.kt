package com.example.anime_screen.anime_screen.sections

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.design_system.custom_modifiers.noRippleClickable
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mTypography

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun DescriptionSection(
    description: String
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .animateContentSize()
            .padding(horizontal = 16.dp)
    ) {
        val animatedColor by animateColorAsState(
            targetValue = if(expanded) mColors.onBackground else mColors.background,
            label = "Animated color"
        )
        if(!expanded) {
            Text(
                text = AnnotatedString.fromHtml(description),
                style = mTypography.bodyMedium.copy(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            mColors.onBackground,
                            animatedColor
                        )
                    )
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        } else {
            Text(
                text = AnnotatedString.fromHtml(description),
                style = mTypography.bodyMedium
            )
        }

        val animatedImage = AnimatedImageVector.animatedVectorResource(NekoViewIcons.ArrowDownAnimated)
        val animatedPainter = rememberAnimatedVectorPainter(animatedImageVector = animatedImage, atEnd = !expanded)
        Image(
            painter = animatedPainter,
            contentDescription = null,
            colorFilter = ColorFilter.tint(mColors.onBackground),
            modifier = Modifier
                .noRippleClickable {
                    expanded = !expanded
                }
        )
    }
}