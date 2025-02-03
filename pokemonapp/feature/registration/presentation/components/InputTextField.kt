package com.kotlin.pokemonapp.feature.registration.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

@Composable
fun InputTextField(
    value: String,
    modifier: Modifier = Modifier,
    label: String = "",
    enabled: Boolean = true,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 70.dp),
            minLines = 1,
            enabled = enabled,
            singleLine = true,
            isError = errorMessage != null,
            value = value,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            interactionSource = interactionSource,

            visualTransformation = visualTransformation,
            onValueChange = onValueChange,
            label = {
                if (errorMessage == null) {
                    Text(label)
                }
            }
        )

        if (errorMessage != null) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp, top = 6.dp),
                text = errorMessage,
                overflow = TextOverflow.Visible,
            )
        }
    }
}

@Preview
@Composable
fun InputTextFieldPreview() {
    PokemonAppTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            InputTextField(
                modifier = Modifier.padding(16.dp),
                value = value,
                onValueChange = { value = it },
                label = "Name"
            )
        }
    }
}

@Preview
@Composable
fun InputTextFieldErrorPreview() {
    PokemonAppTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            InputTextField(
                modifier = Modifier.padding(16.dp),
                value = value,
                errorMessage = "Wrong format",
                onValueChange = { value = it },
                label = "Name"
            )
        }
    }
}
