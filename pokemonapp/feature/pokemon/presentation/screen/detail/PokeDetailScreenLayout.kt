package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonDetailModel
import com.kotlin.pokemonapp.feature.pokemon.presentation.component.ErrorCard
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

@Composable
fun PokeDetailScreenLayout(
    uiState: PokeDetailScreenState,
    onRetryClicked: () -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable(onClick = onBackClicked),
            ) {
                Icon(
                    painter = rememberVectorPainter(Icons.Default.ArrowBack),
                    contentDescription = "",
                )
            }
            when (uiState) {
                is PokeDetailScreenState.Error -> {
                    ErrorCard(
                        message = uiState.message,
                        onRetryClick = onRetryClicked,
                    )
                }

                is PokeDetailScreenState.Idle -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp)
                                .padding(horizontal = 16.dp),
                            text = uiState.detail.name,
                            style = MaterialTheme.typography.headlineLarge,
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp)
                                .padding(horizontal = 16.dp),
                            text = "Height: ${uiState.detail.height}",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp)
                                .padding(horizontal = 16.dp),
                            text = "Weight: ${uiState.detail.weight}",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp)
                                .padding(horizontal = 16.dp),
                            text = "Base experience: ${uiState.detail.baseExperience}",
                            style = MaterialTheme.typography.headlineSmall,
                        )

                    }
                }

                PokeDetailScreenState.Loading -> {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(64.dp)
                    ) {
                        CircularProgressIndicator(
                            Modifier
                                .size(64.dp)
                                .align(Alignment.Center)
                        )
                    }

                }
            }
        }
    }

}

@Preview
@Composable
private fun DetailPreview() {
    PokemonAppTheme {
        PokeDetailScreenLayout(
            uiState = PokeDetailScreenState.Idle(
                detail = PokemonDetailModel(
                    name = "Test",
                    baseExperience = 12,
                    height = 1,
                    weight = 122
                )
            )
        )
    }
}