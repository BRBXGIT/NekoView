package com.example.navbar_screens.home_screen.sections

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.design_system.theme.NekoViewIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    onClearButtonClick: () -> Unit,
    query: String,
    onSearchInput: (String) -> Unit,
    isSearching: Boolean,
    onSearchIconClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    titlesUpdatesLoadingState: Boolean,
    titleByQueryLoadingState: Boolean
) {
    if(isSearching) {
        BackHandler {
            onSearchIconClick()
        }
    }

    Column {
        TopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                if(isSearching) {
                    TextField(
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        value = query,
                        onValueChange = { onSearchInput(it) },
                        placeholder = {
                            Text(
                                text = "Поиск"
                            )
                        },
                        leadingIcon = {
                            IconButton(
                                onClick = onSearchIconClick
                            ) {
                                Icon(
                                    painter = painterResource(NekoViewIcons.ArrowLeftFilled),
                                    contentDescription = null
                                )
                            }
                        }
                    )
                } else {
                    Text(text = "Последние обновления")
                }
            },
            actions = {
                if(!isSearching) {
                    IconButton(
                        onClick = onSearchIconClick
                    ) {
                        Icon(
                            painter = painterResource(NekoViewIcons.Magnifier),
                            contentDescription = null
                        )
                    }
                } else {
                    if(query.isNotBlank()) {
                        IconButton(
                            onClick = onClearButtonClick
                        ) {
                            Icon(
                                painter = painterResource(NekoViewIcons.CloseCircle),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        )

        AnimatedVisibility(
            visible = titlesUpdatesLoadingState or (titleByQueryLoadingState and isSearching),
            enter = fadeIn(tween(300)) + expandVertically(tween(300)),
            exit = fadeOut(tween(300)) + shrinkVertically(tween(300))
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}