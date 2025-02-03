package com.kotlin.pokemonapp.feature.auth.domain

import com.kotlin.pokemonapp.feature.auth.data.datasource.PostManAPIService
import okhttp3.Credentials

interface BasicAuthRepository {
    suspend fun authenticate(userName: String, password: String): Boolean
}

internal class BasicAuthRepositoryImpl(
    private val postmanApi: PostManAPIService,
) : BasicAuthRepository {
    override suspend fun authenticate(userName: String, password: String): Boolean {
        val credentials = Credentials.basic(userName, password)
        return postmanApi.testAuthorization(credentials = credentials).authenticated
    }
}
