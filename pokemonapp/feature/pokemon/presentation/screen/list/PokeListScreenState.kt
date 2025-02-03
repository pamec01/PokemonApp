package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.list


sealed class PokeListScreenUIState {
    data object Loading : PokeListScreenUIState()
    data object Idle : PokeListScreenUIState()
    data class Error(val message: String) : PokeListScreenUIState()
}
