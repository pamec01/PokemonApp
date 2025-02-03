package com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDetailResponseEntity(
    val name: String,
    @Json(name = "base_experience") val baseExperience: Int,
    val height: Int,
    val weight: Int,
)