package com.kotlin.pokemonapp.feature.auth.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReqresPostsResponseEntity(
    val data: List<ReqresPostEntity>
)

@JsonClass(generateAdapter = true)
data class ReqresPostEntity(
    val id: String,
    val name: String,
)