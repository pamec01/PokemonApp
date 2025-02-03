package com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote

import com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.entity.PokemonDetailResponseEntity
import com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.entity.PokemonResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonAPIService {
    @GET("v2/pokemon/")
    suspend fun getPokemon(): PokemonResponseEntity

    @GET("v2/pokemon/")
    suspend fun getPokemonPage(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonResponseEntity

    @GET("v2/pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): PokemonDetailResponseEntity
}