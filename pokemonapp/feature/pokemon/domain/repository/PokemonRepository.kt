package com.kotlin.pokemonapp.feature.pokemon.domain.repository

import androidx.paging.PagingData
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonDetailModel
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemon(): List<PokemonModel>
    suspend fun getPokemonDetail(id: String): PokemonDetailModel

    suspend fun getPokemonPaging(): Flow<PagingData<PokemonModel>>
}
