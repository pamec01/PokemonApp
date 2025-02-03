package com.kotlin.pokemonapp.feature.auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dataState by viewModel.dataState.collectAsStateWithLifecycle()

    when (uiState) {
        is LoginScreenUIState.Error -> {
            Box(Modifier.fillMaxSize()) {
                Column(Modifier.align(Alignment.Center)) {
                    Text(
                        "Error ${(uiState as LoginScreenUIState.Error).message}"
                    )

                    Button(
                        onClick = viewModel::retry
                    ) {
                        Text("Try again")
                    }

                }
            }
        }

        LoginScreenUIState.Idle -> {
            LoginScreenLayout(
                dataState = dataState,
                onLoginEvent = viewModel::onLoginEvent
            )
        }

        LoginScreenUIState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier
                        .size(64.dp)
                        .align(Alignment.Center)
                )
            }
        }

        LoginScreenUIState.Success -> {
            Box(Modifier.fillMaxSize()) {
                Column(Modifier.align(Alignment.Center)) {
                    Text("Login success!!")
                    Button(
                        onClick = viewModel::retry
                    ) {
                        Text("Try again")
                    }
                }
            }
        }

        is LoginScreenUIState.SuccessToken -> {
            Box(Modifier.fillMaxSize()) {
                Column(Modifier.align(Alignment.Center)) {
                    Text("Login success!!")

                    Column {
                        (uiState as LoginScreenUIState.SuccessToken).posts.forEach {
                            Text(it)
                        }
                    }
                    Button(
                        onClick = viewModel::retry
                    ) {
                        Text("Try again")
                    }
                }
            }
        }
    }

}