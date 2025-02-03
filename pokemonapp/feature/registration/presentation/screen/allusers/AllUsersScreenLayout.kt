package com.kotlin.pokemonapp.feature.registration.presentation.screen.allusers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlin.pokemonapp.feature.registration.domain.model.UserModel
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme

@Composable
fun AllUsersScreenLayout(
    uiState: AllUsersScreenState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            AllUsersScreenState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(64.dp)
                        .align(Alignment.Center)
                )
            }

            is AllUsersScreenState.Idle -> {
                LazyColumn(
                    modifier = Modifier.align(Alignment.Center),
                    contentPadding = PaddingValues(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(uiState.users) { item ->
                        Column {
                            Text("name")
                            Text(item.name)
                            Button(onClick = { }) {
                                Text("Go To Detail")
                            }

                            Divider()
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun AllUsersPreview() {
    PokemonAppTheme {
        Surface {
            AllUsersScreenLayout(
                uiState = AllUsersScreenState.Idle(
                    users = listOf(
                        UserModel(
                            0,
                            "Janko Mrkvicka",
                            age = 1,
                        ),
                        UserModel(
                            0,
                            "Peter Pan",
                            age = 1,
                        ),
                    )
                )
            )
        }
    }
}