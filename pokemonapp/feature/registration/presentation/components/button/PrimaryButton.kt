package com.kotlin.pokemonapp.feature.registration.presentation.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    shape: Shape = RoundedCornerShape(24.dp),
    onClick: () -> Unit
) {
    val backgroundColor = if (enabled) {
        Color.Blue
    } else {
        Color.Blue.copy(alpha = 0.1f)
    }

    val contentColor = if (enabled) {
        Color.White
    } else {
        Color.Black.copy(alpha = 0.1f)
    }

    Surface(
        shape = shape,
        color = backgroundColor,
        modifier = modifier.defaultMinSize(minWidth = 240.dp, minHeight = 54.dp),
        enabled = enabled,
        contentColor = contentColor,
        onClick = {
            if (enabled) {
                onClick()
            }
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}

