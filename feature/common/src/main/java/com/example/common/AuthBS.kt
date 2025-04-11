package com.example.common

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.design_system.custom_modifiers.noRippleClickable
import com.example.design_system.theme.NekoViewIcons
import com.example.design_system.theme.mColors
import com.example.design_system.theme.mShapes
import com.example.design_system.theme.mTypography

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationGraphicsApi::class)
@Composable
fun AuthBS(
    onDismissRequest: () -> Unit,
    onAuthButtonClick: (String, String) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        shape = mShapes.small
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(64.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Авторизация",
                    style = mTypography.titleLarge
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    placeholder = { Text("Логин или электронная почта") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(NekoViewIcons.User),
                            contentDescription = null
                        )
                    }
                )

                var isVisible by rememberSaveable { mutableStateOf(false) }
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    placeholder = { Text("Пароль") },
                    visualTransformation = if(!isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(NekoViewIcons.Password),
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { isVisible = !isVisible }
                        ) {
                            val animatedImage = AnimatedImageVector.animatedVectorResource(NekoViewIcons.EyeAnimated)
                            val animatedPainter = rememberAnimatedVectorPainter(animatedImageVector = animatedImage, atEnd = isVisible)

                            Image(
                                painter = animatedPainter,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(mColors.onSurfaceVariant)
                            )
                        }
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onAuthButtonClick(email, password) },
                    shape = mShapes.small
                ) {
                    Text(
                        text = "Авторизоваться"
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .background(
                            color = mColors.surfaceContainerHighest,
                            shape = mShapes.small
                        )
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Text(
                            text = "Новый пользователь? ",
                            style = mTypography.labelLarge
                        )

                        Text(
                            text = "Регистрация",
                            style = mTypography.labelLarge.copy(
                                color = mColors.primary,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.noRippleClickable {
                                //TODO start intent to AnLibria site
                            }
                        )
                    }
                }
            }
        }
    }
}