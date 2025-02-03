package com.kotlin.pokemonapp.feature.registration.presentation.screen.registartion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.pokemonapp.feature.registration.presentation.components.InputTextField
import com.kotlin.pokemonapp.feature.registration.presentation.components.button.PrimaryButton
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

@Composable
fun RegistrationScreenLayout(
    registrationDataState: RegistrationScreenDataState,
    onRegistrationEvent: (RegistrationScreenEvent) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val errorText = "Error occurred!"

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(
                16.dp, alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Register",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Register to our amazing product, no credit card...",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
            )

            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                value = registrationDataState.name,
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(
                        FocusDirection.Next
                    )
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
                ),
                label = "Name",
                errorMessage = if (registrationDataState.nameError) errorText else null,
                onValueChange = { onRegistrationEvent(RegistrationScreenEvent.NameChanged(it)) }
            )

            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                value = registrationDataState.email,
                keyboardActions = KeyboardActions(onGo = {
                    onRegistrationEvent(
                        RegistrationScreenEvent.SubmitClicked
                    )
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go, keyboardType = KeyboardType.Email
                ),
                errorMessage = if (registrationDataState.emailError) errorText else null,
                label = "Email",
                onValueChange = { onRegistrationEvent(RegistrationScreenEvent.EmailChanged(it)) },
            )
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = "Register now",
                onClick = { onRegistrationEvent(RegistrationScreenEvent.SubmitClicked) },
                enabled = registrationDataState.submitButtonEnabled,
            )
        }

        registrationDataState.errorDialog?.let {
            ErrorDialog(
                message = it,
                onDismiss = { onRegistrationEvent(RegistrationScreenEvent.ErrorDialogDismiss) })
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ErrorDialog(message: String, onDismiss: () -> Unit) {
    BasicAlertDialog(onDismissRequest = onDismiss) {
        Box(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier.padding(24.dp).background(color = Color.White, shape = RoundedCornerShape(24.dp))
            ) {
                Text("Error")
                Text(message)
                Button(onDismiss) {
                    Text("Dismiss")
                }
            }
        }

    }
}

@Preview
@Composable
internal fun RegistrationScreenPreview() {
    PokemonAppTheme {
        RegistrationScreenLayout(
            registrationDataState = RegistrationScreenDataState(
                email = "samuel.bily@megumethod.com",
                emailError = true,
                name = "",
                nameError = false,
                password = "1233",
                passwordAgain = "123",
                passwordError = true,
            )
        ) { }
    }
}
