package com.example.navbar_screens.settings_screen.additional_screens.help_screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

data class DonateItem(
    val text: String,
    val icon: Int,
    val url: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(
    navController: NavController
) {
    val topBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val context = LocalContext.current
    Scaffold(
        topBar = {
            SupportScreenTopBar(
                onNavIconClick = { navController.navigateUp() },
                scrollBehavior = topBarScrollBehavior
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(mColors.background)
            .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        val donateItems = listOf(
            DonateItem(
                text = "Оформить подписку на boosty",
                icon = NekoViewIcons.BoostyMulticolored,
                url = "https://boosty.to/anilibriatv"
            ),
            DonateItem(
                text = "Оформить подписку на Patreon",
                icon = NekoViewIcons.PatreonMulticolored,
                url = "https://www.patreon.com/anilibria"
            ),
            DonateItem(
                text = "Денежный перевод ЮMoney",
                icon = NekoViewIcons.YooMoneyMulticolored,
                url = "" //TODO
            ),
            DonateItem(
                text = "Донат на DonationAlerts",
                icon = NekoViewIcons.DonationAlertsMulticolored,
                url = "https://www.donationalerts.com/r/anilibria"
            ),
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = mColors.primary,
                                fontSize = mTypography.titleMedium.fontSize,
                                fontFamily = mTypography.titleMedium.fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("AniLibria ")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = mColors.onBackground,
                                fontSize = mTypography.titleMedium.fontSize,
                                fontFamily = mTypography.titleMedium.fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("- творческое объединение занимающееся озвучиванием аниме")
                        }
                    }
                )
            }

            item {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = mColors.primary,
                                fontSize = mTypography.titleMedium.fontSize,
                                fontFamily = mTypography.titleMedium.fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Немного о нас\n")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = mColors.onBackground,
                                fontSize = mTypography.bodyLarge.fontSize,
                                fontFamily = mTypography.bodyLarge.fontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        ) {
                            append(
                                "-- Один из самых качественных дубляжей аниме, способный потягаться даже с платными сервисами\n" +
                                        "-- Команда более чем из ста человек, любящих своё дело\n" +
                                        "-- Весь контент доступен абсолютно бесплатно\n" +
                                        "-- Есть множество классных приложений\n" +
                                        "-- Мы постоянно совершенствуемся"
                            )
                        }
                    }
                )
            }

            item {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = mColors.primary,
                                fontSize = mTypography.titleMedium.fontSize,
                                fontFamily = mTypography.titleMedium.fontFamily,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Что не круто\n")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = mColors.onBackground,
                                fontSize = mTypography.bodyLarge.fontSize,
                                fontFamily = mTypography.bodyLarge.fontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        ) {
                            append(
                                "-- Наш сайт заблокировал РосКомНадзор, поэтому у проекта сократились доходы"
                            )
                        }
                    }
                )
            }

            item {
                Text(
                    text = "Вы можете поддержать проект материально",
                    style = mTypography.titleMedium.copy(
                        color = mColors.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            items(donateItems) { item ->
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
                                    item.url.toUri()
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
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )

                    Text(
                        text = item.text,
                        style = mTypography.bodyLarge
                    )
                }
            }

            item {
                Text(
                    text = "А также нематериально",
                    style = mTypography.titleMedium.copy(
                        color = mColors.primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            item {
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
                        painter = painterResource(NekoViewIcons.AniLibriaMulticolored),
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )

                    Text(
                        text = "Вступить в команду AniLibria",
                        style = mTypography.bodyLarge
                    )
                }
            }

            item {
                Text(
                    text = "На данный момент все средства пойдут на поддержку сайта, онлайн плеера и в целом на развитие проекта." +
                            "В дальнейшем планируется вернуть денежные премии для участников команды",
                    style = mTypography.labelMedium
                )
            }
        }
    }
}