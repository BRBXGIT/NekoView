package com.example.support_screen.sections

import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinTeamBS(
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        onDismissRequest = onDismissRequest,
        shape = mShapes.small
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "Вступить в команду AniLibria",
                style = mTypography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append("Какие роли у нас есть:\n")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = mColors.primary,
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "Войсер "
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "- озвучивает персонажей\n"
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            color = mColors.primary,
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "Саббер "
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "- переводит субтитры\n"
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            color = mColors.primary,
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "Технарь "
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "- делает хардсаб, накладывает звуковые эффекты и сводит звуковые дорожки\n"
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            color = mColors.primary,
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "Сидер "
                        )
                    }

                    withStyle(
                        style = SpanStyle(
                            fontSize = mTypography.bodyLarge.fontSize,
                            fontFamily = mTypography.bodyLarge.fontFamily,
                        )
                    ) {
                        append(
                            "- раздаёт серии через torrent"
                        )
                    }
                }
            )

            val context = LocalContext.current
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(mShapes.small)
                        .clickable {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    "https://t.me/joinlibria_bot".toUri()
                                )
                            )
                        }
                        .border(
                            width = 1.dp,
                            shape = mShapes.small,
                            color = mColors.tertiaryContainer
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(NekoViewIcons.TelegramMulticolored),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                    Text(
                        text = "Подать заявку через Telegram",
                        style = mTypography.bodyLarge
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(mShapes.small)
                        .clickable {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    "https://youtu.be/J4AKmleW7Ls".toUri()
                                )
                            )
                        }
                        .border(
                            width = 1.dp,
                            shape = mShapes.small,
                            color = mColors.tertiaryContainer
                        )
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(NekoViewIcons.YouTubeMulticolored),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )

                    Text(
                        text = "Первые шаги для озвучивания аниме",
                        style = mTypography.bodyLarge
                    )
                }
            }

            Text(
                text = "Не знаете с чего начать озвучивать аниме?" +
                        "Советуем ознакомиться с этим видео от Lupin (руководитель проекта)"
            )
        }
    }
}