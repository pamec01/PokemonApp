package com.kotlin.pokemonapp.feature.pokemon.presentation.screen.detail

import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonDetailModel

sealed class PokeDetailScreenState {
    data object Loading : PokeDetailScreenState()
    data class Idle(val detail: PokemonDetailModel) : PokeDetailScreenState()
    data class Error(val message: String) : PokeDetailScreenState()
}