package com.kotlin.pokemonapp.feature.registration.presentation.screen.registartion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    onSuccess: () -> Unit,
) {
    val viewModel: RegistrationScreenViewModel = koinViewModel()

    val dataState by viewModel.dataState.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        (uiState as? RegistrationScreenState.Success)?.let { state ->
            onSuccess()
        }
    }

    RegistrationScreenLayout(
        registrationDataState = dataState,
        onRegistrationEvent = viewModel::onRegistrationEvent,
    )
}
