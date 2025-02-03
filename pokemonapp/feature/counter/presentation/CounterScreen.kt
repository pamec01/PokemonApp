package com.kotlin.pokemonapp.feature.counter.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun CounterScreen() {
    val viewModel: CounterViewModel = koinViewModel()

    val count by viewModel.counter.collectAsState()
    val fetchedOnDemand by viewModel.fetchedOnDemand.collectAsState()

    CounterScreenLayout(
        count = count,
        fetchedOnDemand = fetchedOnDemand,
        onButtonClicked = { viewModel.onButtonClicked() },
    )
}

@Composable
fun CounterScreenLayout(
    count: String,
    fetchedOnDemand: String,
    onButtonClicked: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(text = count, style = MaterialTheme.typography.bodyLarge)
            HorizontalDivider()
            Text(
                text = fetchedOnDemand,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
            Button(onClick = onButtonClicked) {
                Text("Click me to fetch!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CounterScreenLayout(
        count = "Count preview text",
        fetchedOnDemand = "Fetched on demand text",
        onButtonClicked = {},
    )
}