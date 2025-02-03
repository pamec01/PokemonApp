package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonModel
import com.kotlin.pokemonapp.feature.pokemon.presentation.component.ErrorCard
import com.kotlin.pokemonapp.feature.pokemon.presentation.component.PokemonListCard
import com.kotlin.pokemonapp.ui.theme.PokemonAppTheme
import kotlinx.coroutines.flow.flowOf


/**
 * based of https://www.figma.com/design/qqRHTqS3EjDmeCHCMfdwC0/PokedexApp---2.-kolo?node-id=203-227&node-type=symbol&t=xXPA9RfLNwyt5rj6-0
 */
@Composable
fun PokeListScreenLayout(
    uiState: PokeListScreenUIState,
    pagingData: LazyPagingItems<PokemonModel>,
    onPokeClicked: (id: String) -> Unit = {},
    onRetryClick: () -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            // header
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(horizontal = 16.dp),
                    text = "Poké App",
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
            item {
                Spacer(Modifier)
            }

            when (uiState) {
                is PokeListScreenUIState.Error -> {
                    item {
                        ErrorCard(
                            message = uiState.message,
                            onRetryClick = onRetryClick,
                        )
                    }
                }

                is PokeListScreenUIState.Idle -> {

                    items(pagingData.itemCount) { index ->
                        pagingData[index]?.let { pokemon ->
                            PokemonListCard(
                                pokemonModel = pokemon,
                                modifier = Modifier.clickable(onClick = {
                                    onPokeClicked(pokemon.id)
                                }),
                            )
                        }
                    }
                }

                PokeListScreenUIState.Loading -> {
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(64.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(64.dp)
                                    .align(Alignment.Center)
                            )
                        }

                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun PokeListPreview() {
    PokemonAppTheme {
        PokeListScreenLayout(
            uiState = PokeListScreenUIState.Idle,
            pagingData = flowOf(
                PagingData.from(
                    listOf(
                        PokemonModel(
                            "",
                            "Name"
                        )
                    )
                )
            ).collectAsLazyPagingItems()
        )
    }
}

@Preview
@Composable
private fun PokeListLoadingPreview() {
    PokemonAppTheme {
        PokeListScreenLayout(
            uiState = PokeListScreenUIState.Loading,
            pagingData = flowOf(
                PagingData.from(
                    listOf(
                        PokemonModel(
                            "",
                            "Name"
                        )
                    )
                )
            ).collectAsLazyPagingItems()
        )
    }
}

@Preview
@Composable
private fun PokeListErrorPreview() {
    PokemonAppTheme {
        PokeListScreenLayout(
            uiState = PokeListScreenUIState.Error("Error occurred"),
            pagingData = flowOf(
                PagingData.from(
                    listOf(
                        PokemonModel(
                            "",
                            "Name"
                        )
                    )
                )
            ).collectAsLazyPagingItems()
        )
    }
}