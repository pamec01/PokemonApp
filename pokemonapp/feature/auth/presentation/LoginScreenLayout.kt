package com.kotlin.pokemonapp.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.pokemonapp.feature.registration.presentation.components.InputTextField
import com.kotlin.pokemonapp.feature.registration.presentation.components.button.PrimaryButton
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

@Composable
fun LoginScreenLayout(
    dataState: LoginScreenDataState,
    onLoginEvent: (LoginScreenEvent) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(
                16.dp, alignment = Alignment.Top
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Login",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
            )

            Column {
                LoginType.entries.forEach { type ->
                    val checked = type == dataState.loginType
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = checked, onCheckedChange = {
                            onLoginEvent(LoginScreenEvent.LoginTypeChanged(type))
                        })
                        Text(type.name)
                    }
                }
            }

            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                value = dataState.userName,
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Next
                    )
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
                ),
                label = "Username",
                errorMessage = null,
                onValueChange = { onLoginEvent(LoginScreenEvent.NameChanged(it)) }
            )

            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                value = dataState.password,
                keyboardActions = KeyboardActions(onGo = {
                    onLoginEvent(
                        LoginScreenEvent.LoginClicked
                    )
                }),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go, keyboardType = KeyboardType.Password
                ),
                errorMessage = null,
                label = "Password",
                onValueChange = { onLoginEvent(LoginScreenEvent.PasswordChanged(it)) },
            )
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = "Login",
                onClick = { onLoginEvent(LoginScreenEvent.LoginClicked) },
                enabled = true,
            )
        }
    }
}

@Preview
@Composable
internal fun LoginScreenPreview() {
    PokemonAppTheme {
        LoginScreenLayout(
            dataState = LoginScreenDataState.EMPTY,
        ) { }
    }
}