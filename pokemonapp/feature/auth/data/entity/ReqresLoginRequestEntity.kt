package com.kotlin.pokemonapp.feature.auth.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReqresLoginRequestEntity(
    val email: String,
    val password: String,
)
