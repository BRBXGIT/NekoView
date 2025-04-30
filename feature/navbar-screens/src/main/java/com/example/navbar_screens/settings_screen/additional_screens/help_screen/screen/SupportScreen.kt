package com.example.navbar_screens.settings_screen.additional_screens.help_screen.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mTypography
import com.example.navbar_screens.settings_screen.additional_screens.help_screen.sections.AboutAniLibriaSection
import com.example.navbar_screens.settings_screen.additional_screens.help_screen.sections.AniLibriaDefinitionSection
import com.example.navbar_screens.settings_screen.additional_screens.help_screen.sections.DonateItem
import com.example.navbar_screens.settings_screen.additional_screens.help_screen.sections.JoinTeamBS
import com.example.navbar_screens.settings_screen.additional_screens.help_screen.sections.SupportScreenTopBar
import com.example.navbar_screens.settings_screen.additional_screens.help_screen.sections.WhatsBadSection

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
                url = "" //TODO create yoomoney link
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
                AniLibriaDefinitionSection()
            }

            item {
                AboutAniLibriaSection()
            }

            item {
                WhatsBadSection()
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
                DonateItem(
                    onClick = {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                item.url.toUri()
                            )
                        )
                    },
                    icon = item.icon,
                    text = item.text
                )
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
                var isJoinTeamBSOpened by rememberSaveable { mutableStateOf(false) }
                if(isJoinTeamBSOpened) {
                    JoinTeamBS(
                        onDismissRequest = { isJoinTeamBSOpened = false }
                    )
                }
                DonateItem(
                    onClick = {
                        isJoinTeamBSOpened = true
                    },
                    icon = NekoViewIcons.AniLibriaMulticolored,
                    text = "Вступить в команду AniLibria"
                )
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