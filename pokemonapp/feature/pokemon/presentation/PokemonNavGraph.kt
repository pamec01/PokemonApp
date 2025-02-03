package com.kotlin.pokemonapp.feature.pokemon.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kotlin.pokemonapp.feature.pokemon.presentation.screen.detail.PokeDetailScreen
import com.kotlin.pokemonapp.feature.pokemon.presentation.screen.list.PokeListScreen

@Composable
fun PokemonNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "pokelist",
    ) {
        composable("pokelist") {
            PokeListScreen(
                onPokeClicked = {
                    navController.navigate("detail/$it")
                }
            )
        }

        composable("detail/{id}") { backEntry ->
            backEntry.arguments?.getString("id")?.let {
                PokeDetailScreen(
                    pokemonId = it,
                    onBackClicked = { navController.navigateUp() }
                )
            }
        }
    }
}