package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokeListScreen(
    onPokeClicked: (id: String) -> Unit,
) {
    val viewModel: PokeListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagingData = viewModel.pagingData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.onScreenLoad()
    }
    PokeListScreenLayout(
        uiState = uiState,
        pagingData = pagingData,
        onPokeClicked = onPokeClicked,
        onRetryClick = viewModel::retry
    )
}