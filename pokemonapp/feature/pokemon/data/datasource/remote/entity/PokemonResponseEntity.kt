package com.kotlin.pokemonapp.feature.pokemon.data.datasource.remote.entity

import androidx.core.net.toUri
import com.kotlin.pokemonapp.feature.pokemon.domain.model.PokemonModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponseEntity(
    val count: Long,
    val next: String,
    val previous: Any?,
    val results: List<PokemonBasicEntity>,
)

@JsonClass(generateAdapter = true)
data class PokemonBasicEntity(
    val name: String,
    val url: String,
)

internal fun PokemonBasicEntity.toModel(): PokemonModel {
    val id = url.toUri().lastPathSegment
        ?: throw IllegalStateException("Could not parse id from PokeAPI response")
    return PokemonModel(
        id = id,
        name = name,
    )
}