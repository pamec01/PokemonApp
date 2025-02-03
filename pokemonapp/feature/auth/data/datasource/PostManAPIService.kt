package com.kotlin.pokemonapp.feature.auth.data.datasource

import com.kotlin.pokemonapp.feature.auth.data.entity.PostmanAuthorizationResult
import retrofit2.http.GET
import retrofit2.http.Header

interface PostManAPIService {
    @GET("basic-auth")
    suspend fun testAuthorization(
        @Header("Authorization") credentials: String
    ): PostmanAuthorizationResult
}