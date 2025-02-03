package com.kotlin.pokemonapp.feature.registration.presentation.screen.allusers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllUsersScreen() {
    val viewModel: AllUsersScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onScreenLoaded()
    }

    AllUsersScreenLayout(
        uiState = uiState
    )
}

