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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.example.design_system.theme.NekoViewIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar(
    onSearchClick: (String) -> Unit,
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
                    var query by rememberSaveable { mutableStateOf("") }

                    TextField(
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        value = query,
                        onValueChange = { query = it },
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
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = { onSearchClick(query) }
                        )
                    )
                } else {
                    Text(text = "Последние обновления")
                }
            },
            actions = {
                IconButton(
                    onClick = onSearchIconClick
                ) {
                    Icon(
                        painter = painterResource(NekoViewIcons.Magnifier),
                        contentDescription = null
                    )
                }
            }
        )

        AnimatedVisibility(
            visible = titlesUpdatesLoadingState or titleByQueryLoadingState,
            enter = fadeIn(tween(300)) + expandVertically(tween(300)),
            exit = fadeOut(tween(300)) + shrinkVertically(tween(300))
        ) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}