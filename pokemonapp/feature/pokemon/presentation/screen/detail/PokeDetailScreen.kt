package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PokeDetailScreen(
    pokemonId: String,
    onBackClicked: () -> Unit,
) {
    val viewModel: PokeDetailViewModel = koinViewModel { parametersOf(pokemonId) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onScreenLoaded()
    }

    PokeDetailScreenLayout(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onRetryClicked = viewModel::retry
    )

}